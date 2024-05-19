package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/convert")
public class TextToBinaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String textInput = request.getParameter("textInput");
        int key = Integer.parseInt(request.getParameter("key"));

        StringBuilder binaryOutput = new StringBuilder();
        for (char c : textInput.toCharArray()) {
            if (c == ' ') {
                // Represent spaces as zeros based on the key length
                for (int i = 0; i < key; i++) {
                    binaryOutput.append("0");
                }
            } else {
                int charPosition = c - 'A' + 1;
                String binaryString = Integer.toBinaryString(charPosition);
                // Pad the binary string with leading zeros to match the specified key length
                while (binaryString.length() < key) {
                    binaryString = "0" + binaryString;
                }
                binaryOutput.append(binaryString);
            }
        }

        response.setContentType("text/plain");
        response.getWriter().write("Binary Output: " + binaryOutput.toString());
    }
}
