package com.example.cpp_iap_remove_proj;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("cpp_iap_remove_proj"); // имя .so библиотеки
    }

    // JNI метод с параметром
    public native int secondStepCalc(int input);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText inputField = findViewById(R.id.input_field);
        Button checkBtn = findViewById(R.id.check_btn);

        checkBtn.setOnClickListener(v -> {
            String inputStr = inputField.getText().toString().trim();

            if (inputStr.isEmpty()) {
                Toast.makeText(this, "Введите число", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int input = Integer.parseInt(inputStr);

                int first = 2 + 2; // Java-этап
                int second = secondStepCalc(input); // C++-этап
                int result = first + second;

                Log.d("ptg", "Final result: " + result);
                Toast.makeText(this, "Result: " + result, Toast.LENGTH_SHORT).show();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Неверный формат числа", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
