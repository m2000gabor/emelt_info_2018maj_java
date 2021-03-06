/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netbeansfirsttestproject;

import java.io.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import static java.time.temporal.ChronoField.MILLI_OF_SECOND;
import java.util.*;

/**
 *
 * @author test
 */
public class NetBeansFirstTestProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String workingDic = System.getProperty("user.dir");
        Ember[] emberek =beolvasas(workingDic);
        int recordsLenght = emberek.length;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        //masodik feladat
        int uccsoKi = 00;
        for(int x=recordsLenght-1; emberek[x].getDirection().equals("be") ;x--){
            uccsoKi=emberek[x-1].getId(); }       
        System.out.println("2. Feladat\nAz első belépő: "+ emberek[0].getId() + "\nAz utolsó kilépő: " + uccsoKi);

        
        //harmadik feladat
        String[] arr3 = new String[100];
        for(int x=1;x<=100;x++){
        int res =harmadik(x,emberek);
        if(res!=0){arr3[x-1]=x + " "+Integer.toString(res)+" \r\n";}
        }
        try{Formatter formatter = new Formatter(workingDic+"\\athaladas.txt");
        for(int y=0;y<100;y++){if(arr3[y]!=null){formatter.format("%s", arr3[y]);}} 
        formatter.close();}catch( IOException e ){ System.out.println("Az írás kérése nem hajtható végre");}
        
        
        //negyedik feladat
        System.out.print("\n4. Feladat\nA végén a társalgóban voltak: ");
        for(int x =0;x<100;x++){
        if(negyedik(emberek)[x]!=0){
        System.out.print(negyedik(emberek)[x]+" ");}}
        System.out.print("\n");
        
         
        //otodik feladat
        System.out.println("\n5. Feladat\nPéldául "+otodik(emberek)+ "-kor voltak a legtöbben a társalgóban.");
        
 
        //hatodik feladat
        int userInputId = hatodik();
        
        //hetedik feladat
        System.out.print("\n7. Feladat");
        Time[] idopontok =new Time[recordsLenght];
        int elofordulas =0;
        for(int x=0;x<recordsLenght;x++){
        Ember actualItem =emberek[x];    
        if(actualItem.getId()==userInputId){
            idopontok[elofordulas]= actualItem.getIdo();
            elofordulas++;}
        }
        for(int x=0;x<recordsLenght;x++){
        if(idopontok[x]!=null){
            System.out.print("\n"+sdf.format(idopontok[x]) +"-");}
        x++;
        if(idopontok[x]!=null){
            System.out.print(sdf.format(idopontok[x]));}
        }
        
        //nyolcadik feladat
        sdf.applyPattern("mm");
        String bentIdo =sdf.format(new Time(nyolcadikIdo(idopontok,elofordulas)-3600000));
        String vegen ="a megfigyelési időszak végén nem volt bent a társalgóban.";
        if(nyolcadikBool(elofordulas)){vegen="a megfigyelés végén a társalgóban volt.";}
        System.out.println("\n\n8. Feladat");
        System.out.println("A(z) "+ userInputId +". személy összesen "+bentIdo+" percet volt bent, "+vegen);
}
    
    public static Ember[] beolvasas(String workingDictionary){
       int hossz =0;
      try
    { 
        File file = new File(workingDictionary+"\\ajto.txt");
        Scanner inputReader = new Scanner(file);
       for(int x =0;inputReader.hasNextInt();x++){
           inputReader.nextInt();inputReader.nextInt();inputReader.nextInt();inputReader.next();
           hossz++;
       }
       
         Ember[] emberek = new Ember[hossz]; 
        Scanner sc = new Scanner(file);
    
        for(int x=0;sc.hasNext();x++){
          Ember emb = new Ember(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.next());
          emberek[x]=emb; }
         return emberek;
      }catch( IOException e ){ System.out.println("A keresett fájl nem található");}
     return null;
}
    public static int harmadik(int id, Ember[] emb){
        int elofordulas =0;
        for(int x =0;x<emb.length;x++){
        if(emb[x].getId()==id){elofordulas++;}
        }
    return elofordulas;
    }
    public static int[] negyedik(Ember[] adat){
        int[] ret = new int[100];
        int retActLength =0;
        for(int x=1;x<100;x++){
            //false=kint true=bent
            boolean pozicio = false;
            for(int y=0;y<adat.length;y++){
            Ember aktEmb = adat[y];
            if(aktEmb.getId()==x){
              if(aktEmb.getDirection().equals("be")){pozicio = true;}
              if(aktEmb.getDirection().equals("ki")){pozicio = false;}
            }}
        if(pozicio){ret[retActLength]=x;retActLength++;}
        }
    return ret;}
    public static String otodik(Ember[] adat){
    String ret ="unknown";
    int rekordLetszam = 0;
    int actualLetszam = 0;
    Time rekordTime= new Time(0);
    for(int x=0;x<adat.length;x++){
    Ember actE = adat[x];
    if(actE.getDirBoolean()){actualLetszam++;}
    else{actualLetszam--;}
    
    if(actualLetszam>rekordLetszam){rekordTime = actE.getIdo(); rekordLetszam=actualLetszam;}
    }
    //System.out.println("Letszam maximuma:"+rekordLetszam);
    return rekordTime.toString();}
    public static int hatodik(){
        System.out.print("\n6. Feladat\nAdja meg a személy azonosítóját! ");
        Scanner userInpSc = new Scanner(System.in);
        int inp = userInpSc.nextInt();
       // System.out.print(inp+"\n");
        return inp;
    }
    public static long nyolcadikIdo(Time[] idok,int elof){
    long osszesen =0;  
    long kulonbseg;
    for(int x=0;x<elof;x++){
    if(idok[x+1]!=null){kulonbseg=idok[x+1].getTime()-idok[x].getTime();
    x++;}
    else{kulonbseg=50400000-idok[x].getTime();}
    osszesen=osszesen+kulonbseg;}
    return osszesen;}
    public static boolean nyolcadikBool(int eloford){return eloford%2 ==1;}
}