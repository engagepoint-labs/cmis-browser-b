package simplejsf.web;

import simplejsf.core.Log;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("simplejsf.NonDigitsValidator")
public class NonDigitsValidator implements Validator
{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
    {
        Log.println("NonDigitsValidator.validate()");

        String pattern = "^(\\D+)$";
        String firstname = (String)value;

        if (!firstname.matches(pattern))
        {
            FacesMessage message = new FacesMessage();

            message.setSeverity(FacesMessage.SEVERITY_WARN);
            message.setSummary("Warning");
            message.setDetail("Digits are not allowed.");

            throw new ValidatorException(message);
        }
    }
}
