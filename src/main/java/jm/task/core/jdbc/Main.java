package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Scanner;

public class Main {
    private static boolean flag = true;
    private static boolean flagSecond = true;

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Приветствую!" +
                "\nЖелаете создать таблицу?" +
                "\n Y/N");

        while (flag) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("y")) {
                userService.createUsersTable();
                System.out.println("Таблица Users создана!");

                while (flag) {
                    System.out.println("\nЧего соизволите сделать дальше?" +
                            "\n1) Добавить пользователя" +
                            "\n2) Удалить пользователя" +
                            "\n3) Вывести список всех пользователей" +
                            "\n4) Очистить таблицу" +
                            "\n5) Удалить таблицу" +
                            "\n6) Выход из программы =(");

                    switch (scanner.nextInt()) {
                        case 1:
                            System.out.println("Введите имя:");
                            String name = scanner.next();
                            System.out.println("Введите фамилию:");
                            String lastName = scanner.next();

                            byte age = 0;
                            boolean isValid = false;

                            while (!isValid) {
                                try {
                                    System.out.println("Введите возраст: ");
                                    age = scanner.nextByte();

                                    if (age >= 0 && age <= 126) {
                                        isValid = true;
                                    } else {
                                        System.out.println("Возраст должен быть от 0 до 126!");
                                    }
                                } catch (Exception e) {
                                    System.out.println("\nНекорректный ввод!" +
                                            "\nДавайте попробуем еще раз");
                                    scanner.nextLine();
                                }
                            }

                            userService.saveUser(name, lastName, age);
                            System.out.println("Пользователь " + name + " " + lastName + " добавлен");
                            break;
                        case 2:
                            System.out.println("Введите индекс пользователя:");
                            int id = scanner.nextInt();
                            userService.removeUserById(id);
                            System.out.println("Пользователь с индексом " + id + " удален");
                            break;
                        case 3:
                            System.out.println("Список всех пользователей:");
                            userService.getAllUsers().forEach(System.out::println);
                            break;
                        case 4:
                            userService.cleanUsersTable();
                            System.out.println("Таблица отчищена");
                            break;
                        case 5:
                            userService.dropUsersTable();
                            System.out.println("Таблица удалена");

                            System.out.println("\nЖелаете создать новую таблицу?" +
                                    "\nY/N");
                            while (flagSecond) {
                                String inputSecond = scanner.next();
                                if (inputSecond.equalsIgnoreCase("y")) {
                                    userService.createUsersTable();
                                    break;
                                } else if (inputSecond.equalsIgnoreCase("n")) {
                                    System.out.println("Завершение программы!" +
                                            "\nДо свидания!");
                                    flagSecond = false;
                                    flag = false;
                                } else {
                                    System.out.println("Чуток мимо =)" +
                                            "\nДавайте попробуем еще раз" +
                                            "\nY/N?");
                                }
                            }
                            break;
                        case 6:
                            System.out.println("Завершение программы!" +
                                    "\nДо свидания!");
                            flag = false;
                            break;
                        default:
                            System.out.println("Чуток мимо =)" +
                                    "\nДавайте попробуем еще раз");
                    }
                }
            } else if (input.equalsIgnoreCase("N")) {
                System.out.println("Завершение программы!" +
                        "\nДо свидания!");
                flag = false;
            } else {
                System.out.println("Чуток мимо =)" +
                        "\nДавайте попробуем еще раз" +
                        "\nY/N?");
            }
        }
        scanner.close();
    }
}