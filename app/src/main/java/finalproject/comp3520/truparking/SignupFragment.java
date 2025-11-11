package finalproject.comp3520.truparking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class SignupFragment extends Fragment
{
    private TextInputEditText fullNameInput, studentNumberInput, passwordInput, confirmPasswordInput;
    private TextInputLayout fullNameLayout, studentNumberLayout, passwordLayout, confirmPasswordLayout;
    private CheckBox termsCheckbox;
    private MaterialButton createAccountButton;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        fullNameInput = view.findViewById(R.id.nameEditText);
        fullNameLayout = view.findViewById(R.id.nameInputLayout);
        studentNumberInput = view.findViewById(R.id.studentNumberEditText);
        studentNumberLayout = view.findViewById(R.id.studentNumberInputLayout);
        passwordInput = view.findViewById(R.id.passwordEditText);
        passwordLayout = view.findViewById(R.id.passwordInputLayout);
        confirmPasswordInput = view.findViewById(R.id.confirmPasswordEditText);
        confirmPasswordLayout = view.findViewById(R.id.confirmPasswordInputLayout);
        termsCheckbox = view.findViewById(R.id.termsCheckbox);
        createAccountButton = view.findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(v -> validateInput());

        return view;
    }

    private void validateInput()
    {
        String fullName = fullNameInput.getText().toString().trim();
        String studentNumber = studentNumberInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        boolean valid = true;
        fullNameLayout.setError(null);
        studentNumberLayout.setError(null);
        passwordLayout.setError(null);
        confirmPasswordLayout.setError(null);

        if (fullName.isEmpty())
        {
            fullNameLayout.setError("Full name cannot be empty");
            valid = false;
        }
        else if (!fullName.matches("^[A-Za-z]+$"))
        {
            fullNameLayout.setError("Full name cannot contain numbers or special characters");
            valid = false;
        }

        if (studentNumber.isEmpty())
        {
            studentNumberLayout.setError("Student number cannot be empty");
            valid = false;
        }
        else if (studentNumber.charAt(0) != 'T' && studentNumber.charAt(0) != 't')
        {
            studentNumberLayout.setError("Student number must start with 'T'");
            valid = false;
        }
        else if (studentNumber.length() != 9)
        {
            studentNumberLayout.setError("Student number should be a 8 digit number");
            valid = false;
        }

        if (password.isEmpty())
        {
            passwordLayout.setError("Password cannot be left blank");
            valid = false;
        }
        else if (!password.matches(".*[A-Z].*"))
        {
            passwordLayout.setError("Password must contain at least one capital letter");
            valid = false;
        }
        else if (!password.matches(".*\\d.*"))
        {
            passwordLayout.setError("Password must contain at least one number");
            valid = false;
        }
        else if (password.length() < 8)
        {
            passwordLayout.setError("Password must be at least 8 characters long");
            valid = false;
        }

        if (confirmPassword.isEmpty())
        {
            confirmPasswordLayout.setError("Confirm password cannot be blank");
            valid = false;
        }
        else if (!confirmPassword.equals(password))
        {
            confirmPasswordLayout.setError("Password and confirm password do not match");
            valid = false;
        }

        if (!termsCheckbox.isChecked())
        {
            termsCheckbox.setError("You must agree to the terms and conditions");
            valid = false;
        }


        if (valid)
        {
            Toast.makeText(getContext(), "Successfully logged in", Toast.LENGTH_LONG).show();
        }

    }

}