package com.dayao.prep18.cucumber.stepdefs;

import com.dayao.prep18.Captura18App;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = Captura18App.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
