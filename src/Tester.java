public class Tester {
    public static void main(String[] args) {
        System.out.println();

        notRunner run = new notRunner();
/*
       //Creating Drives
        System.out.println(run.createDrive("SDA1", 30));
        System.out.println(run.createDrive("SDA2", 30));
        System.out.println(run.createDrive("SDB1", 30));
        System.out.println(run.createDrive("SDB2", 30));
        System.out.println(run.createDrive("SDA1", 30)); //Drive name is already taken
*/
/*      System.out.println();
        //Creating PVs
        System.out.println(run.createPV("PV1", "SDA1"));
        System.out.println(run.createPV("PV2", "SDA2"));
        System.out.println(run.createPV("PV3", "SDB1"));
        System.out.println(run.createPV("PV1", "SDB2")); //PV name is already taken
        System.out.println(run.createPV("PV4", "SDA1")); //Drive belongs to another PV
        System.out.println(run.createPV("PV5", "askdjlasjdla")); //Drive does not exist
*/
/*      System.out.println();
        //Creating VGS
        System.out.println(run.createVG("VG1", "PV1"));
        System.out.println(run.createVG("VG2", "PV2"));
        System.out.println(run.createVG("VG1", "PV3")); //VG name is already taken
        System.out.println(run.createVG("VG3", "PV1")); //PV belongs to a different VG
        System.out.println(run.createVG("VG4", "akdhsads")); //PV does not exist
*/
/*      System.out.println();
        //Creating LVS
        System.out.println(run.createLV("LV1", 10, "VG1"));
        System.out.println(run.createLV("LV2", 10, "VG2"));
        System.out.println(run.createLV("LV1", 10, "VG2")); //LV name is already taken
        System.out.println(run.createLV("LV3", 100000, "VG1")); //Not enough space
        System.out.println(run.createLV("LV3", 10, "asdadasa")); //VG does not exist
*/
/*      System.out.println();
        //Extending VGs
        System.out.println(run.extendVG("VG1", "PV3"));
        System.out.println(run.extendVG("VG2", "PV3")); //PV belongs to a different VG
        System.out.println(run.extendVG("asdaasd", "PV3")); //VG does not exist
        System.out.println(run.extendVG("VG1", "asdadsewf")); //PV does not exist
*/
/*
        //Printing stuff
        run.createDrive("driveA1", 30);
        run.createDrive("driveA2", 30);
        run.createDrive("driveA3", 30);
        run.createDrive("driveA4", 30);

        run.createDrive("driveB1", 30);
        run.createDrive("driveB2", 30);
        run.createDrive("driveB3", 30);
        run.createDrive("driveB4", 30);

        run.createPV("PVA1", "driveA1");
        run.createPV("PVA2", "driveA2");


        run.createPV("PVB1", "driveB1");
        run.createPV("PVB2", "driveB2");
        run.createPV("PVB3", "driveB3");
        run.createPV("PVB4", "driveB4");

        run.createPV("PVA3", "driveA3");
        run.createPV("PVA4", "driveA4");

        run.createVG("VGA", "PVA1");
        run.extendVG("VGA", "PVA2");
        run.extendVG("VGA", "PVA3");
        run.extendVG("VGA", "PVA4");

        run.createVG("VGB", "PVB1");
        run.extendVG("VGB", "PVB2");
        //run.extendVG("VGB", "PVB3");
        //run.extendVG("VGB", "PVB4");

        run.createLV("LVA1", 30, "VGA");


        run.createLV("LVB1", 30, "VGB");
        run.createLV("LVB2", 30, "VGB");

        run.createLV("LVA2", 30, "VGA");


        //run.printPVs();
        //run.printDrives();
        //run.printVGs();
        //run.printLVs();
*/
    }
}
