package com.company;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        DrawPanel panel = new DrawPanel();
        this.add(panel);

    }
}