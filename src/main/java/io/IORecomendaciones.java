package main.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class IORecomendaciones
{
    public static String getRecomendacion(String tipoUsuario)
    {
        HashMap<Integer, String> hmUsuario = new HashMap<Integer, String>();
        HashMap<Integer, String> hmPsicologo = new HashMap<Integer, String>();
        String recomendacion = "¡Vaya! No se ha encontrado una recomendación para ti hoy";
        try
        {
            FileReader fr = new FileReader("images/recomendaciones.csv");
            BufferedReader br = new BufferedReader(fr);
            String linea = null;
            while((linea = br.readLine()) != null)
            {
                String s[] = linea.split(",");
                String tipo = s[0];
                Integer numFrase = Integer.parseInt(s[1]);
                String reco = s[2];
                if (tipo.equals("&"))
                    hmUsuario.put(numFrase, reco);
                else
                    hmPsicologo.put(numFrase,reco);
            }
        }
        catch(IOException ioe)
        {
            System.out.println("¿Fichero dañado?");
            ioe.printStackTrace();
        }

        if (tipoUsuario == "usuario")
        {
            Set<Integer> keySet = hmUsuario.keySet();
            List<Integer> keyList = new ArrayList<>(keySet);

            int indice = (int) (Math.random() * keyList.size());

            Integer randomKey = keyList.get(indice);
            recomendacion = hmUsuario.get(randomKey);
        }
        else
        {
            Set<Integer> keySet = hmPsicologo.keySet();
            List<Integer> keyList = new ArrayList<>(keySet);

            int indice = (int) (Math.random() * keyList.size());

            Integer randomKey = keyList.get(indice);
            recomendacion = hmPsicologo.get(randomKey);
        }
        return recomendacion;
    }
}
