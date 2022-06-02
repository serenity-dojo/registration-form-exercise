package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;
import net.serenitybdd.screenplay.ui.PageElement;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationStepDefinitions {
    @Given("{actor} wants to showcase his application")
    public void openRegistrationPage(Actor actor) {
        actor.attemptsTo(
                Open.url("https://datamall.lta.gov.sg/content/datamall/en/app-zone-submission.html")
        );

    }

    private String missingField;
    @When("{actor} submits his application without completing the {} field")
    public void submitApplicationWithoutField(Actor actor, String missingField) {
        this.missingField = missingField;
        actor.attemptsTo(
                Clear.field(InputField.withPlaceholder(missingField)),
                Click.on(Button.withText("Submit"))
        );
    }

    @Then("{actor} should be presented with the error message {string}")
    public void shouldSeeError(Actor actor, String expectedError) {
        Target missingFieldErrorMessageField = PageElement.located(By.cssSelector("label.error"))
                   .inside(PageElement.withCSSClass("row").containingText(missingField));

        String errorMessage = actor.asksFor(Text.of(missingFieldErrorMessageField));

        assertThat(errorMessage).isEqualTo(expectedError);
    }


}
