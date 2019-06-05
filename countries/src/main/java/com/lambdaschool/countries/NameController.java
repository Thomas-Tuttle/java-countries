package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
@RequestMapping("/names")
public class NameController
{
    // localhost:2019/names/all
    @GetMapping(value = "all")
    public ResponseEntity<?> getAllCountries()
    {
        CountriesApplication.countryList.countryList.sort((c1, c2 ) -> c1.getName().compareToIgnoreCase(c2.getName()));

        return new ResponseEntity<>(CountriesApplication.countryList.countryList, HttpStatus.OK);
    }


    // localhost:2019/names/start/a
    @GetMapping(value = "/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesWithStartLetter(
            @PathVariable
                    char letter)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.countryList.
                findCountries(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        rtnCountries.sort((c1, c2 ) -> c1.getName().compareToIgnoreCase(c2.getName()));

        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }


    // localhost:2019/names/size/12
    @GetMapping(value = "/size/{number}")
    public ResponseEntity<?> getCountriesNameSize(@PathVariable int number)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.countryList.
                findCountries(c -> c.getName().length() >= number);
        rtnCountries.sort((c1, c2 ) -> c1.getName().compareToIgnoreCase(c2.getName()));

        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }
}