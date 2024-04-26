package com.isaura.model;

public class CleaningTable {

    private String[][] cleaningSchedule = new String[5][5];
    private Member member;
    private Cleaning cleaning;

    public CleaningTable() {}

    public CleaningTable(String p00, String p01, String p02, String p03, String p04,
                          String p10, String p11, String p12, String p13, String p14,
                          String p20, String p21, String p22, String p23, String p24,
                          String p30, String p31, String p32, String p33, String p34,
                          String p40, String p41, String p42, String p43, String p44,
                          String p50, String p51, String p52, String p53, String p54) {

        this.cleaningSchedule[0][0] = p00;
        this.cleaningSchedule[0][1] = p01;
        this.cleaningSchedule[0][2] = p02;
        this.cleaningSchedule[0][3] = p03;
        this.cleaningSchedule[0][4] = p04;

        this.cleaningSchedule[1][0] = p10;
        this.cleaningSchedule[1][1] = p11;
        this.cleaningSchedule[1][2] = p12;
        this.cleaningSchedule[1][3] = p13;
        this.cleaningSchedule[1][4] = p14;

        this.cleaningSchedule[2][0] = p20;
        this.cleaningSchedule[2][1] = p21;
        this.cleaningSchedule[2][2] = p22;
        this.cleaningSchedule[2][3] = p23;
        this.cleaningSchedule[2][4] = p24;

        this.cleaningSchedule[3][0] = p30;
        this.cleaningSchedule[3][1] = p31;
        this.cleaningSchedule[3][2] = p32;
        this.cleaningSchedule[3][3] = p33;
        this.cleaningSchedule[3][4] = p34;

        this.cleaningSchedule[4][0] = p40;
        this.cleaningSchedule[4][1] = p41;
        this.cleaningSchedule[4][2] = p42;
        this.cleaningSchedule[4][3] = p43;
        this.cleaningSchedule[4][4] = p44;

        this.cleaningSchedule[5][0] = p50;
        this.cleaningSchedule[5][1] = p51;
        this.cleaningSchedule[5][2] = p52;
        this.cleaningSchedule[5][3] = p53;
        this.cleaningSchedule[5][4] = p54;
    }

    public String[][] getCleaningSchedule() {
        return cleaningSchedule;
    }

    public void setCleaningSchedule(String[][] cleaningSchedule) {
        this.cleaningSchedule = cleaningSchedule;
    }
}
