package com.spotlightscout.controller;

import com.spotlightscout.model.Player;
import com.spotlightscout.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

@Controller
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/players")
    public String getAllPlayers(Model model) {
        List<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return "players"; // maps to players.html
    }

    @PostMapping("/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/players";
        }

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            reader.readNext(); // skip header
            while ((line = reader.readNext()) != null) {
                Player p = new Player();
                p.setName(line[0]);
                p.setPosition(line[1]);
                p.setMinutesPlayed(Integer.parseInt(line[2]));
                p.setAssists(Integer.parseInt(line[3]));
                p.setTackles(Integer.parseInt(line[4]));
                p.setPassAccuracy(Double.parseDouble(line[5]));
                playerRepository.save(p);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Error uploading file.");
            return "redirect:/players";
        }

        redirectAttributes.addFlashAttribute("message", "Upload successful!");
        return "redirect:/players";
    }
}


