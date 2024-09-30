package org.example.infrastructure.utils;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class ScannerUtil {

    public final Scanner scanner;

    public String getClientInput() {
        return scanner.nextLine();
    }

}
