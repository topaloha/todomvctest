package ua.com.topaloha;


import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TodoMVCTest {

    @Test
    public void testCreateTask() {
        //Configuration.browser = "chrome";
        //Configuration.timeout = 2000;

        //Create fore tasks and check result
        open("https://todomvc4tasj.herokuapp.com/");
        $("#new-todo").val("task1").pressEnter();
        $("#new-todo").val("task2").pressEnter();
        $("#new-todo").val("task3").pressEnter();
        $("#new-todo").val("task4").pressEnter();

        $$("#todo-list li").shouldHave(exactTexts("task1", "task2", "task3", "task4"));

        //Delete task2 and check result
        $$("#todo-list li").findBy(text("task2")).hover().$("button").click();

        $$("#todo-list li").shouldHave(exactTexts("task1", "task3", "task4"));

        //Mark task4 as completed and check result
        $$("#todo-list li").findBy(text("task4")).$("input").click();

        $$("#todo-list li").findBy(text("task4")).$("input").shouldBe(selected);

        //Clear completed and check result
        $("#clear-completed").click();

        $$("#todo-list li").findBy(text("task4")).$("input").shouldNot(exist);

        //Mark all as completed and check result
        $("#toggle-all").click();

        $$("#todo-list li").findBy(text("task1")).$("input").shouldBe(selected);
        $$("#todo-list li").findBy(text("task3")).$("input").shouldBe(selected);

        //Clear completed and check result
        $("#clear-completed").click();

        $$("#todo-list li").findBy(text("task1")).$("input").shouldNot(exist);
        $$("#todo-list li").findBy(text("task3")).$("input").shouldNot(exist);
        $$("#todo-list li").findBy(text("task4")).$("input").shouldNot(exist);

    }
}


































