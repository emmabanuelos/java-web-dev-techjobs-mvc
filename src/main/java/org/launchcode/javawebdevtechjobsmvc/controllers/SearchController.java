package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @RequestMapping(value = "results", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String displaySearchResults(Model model,@RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("SearchType", searchType);
        model.addAttribute("SearchTerm", searchTerm);
        //the arraylist needs to be put into the code first because the results needs to have a place to go before they into the array
        ArrayList<Job> jobs = new ArrayList<>();

        if (searchTerm.toLowerCase().equals("all")) {
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");

        }else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType + ": " + searchTerm));
        }
        model.addAttribute("colums", columnChoices);
        model.addAttribute("jobs", jobs);

        return "search";
    }


}
