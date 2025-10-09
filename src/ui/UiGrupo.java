package ui;

import controle.Sistema;

import java.util.Random;
import java.util.Scanner;

public class UiGrupo {
    protected Random rnd;
    protected Scanner scn;
    protected Sistema sistema;

    public UiGrupo() {
        rnd = new Random();
        scn = new Scanner(System.in);
        sistema = Sistema.getInstance();
    }

    public void adicionar() {


    }

    public void excluir() {
    }
}
