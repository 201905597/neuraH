package main.java.io;

import java.io.*;
import java.net.URL;
import java.util.*;

public class IORecomendaciones
{
    /**
     * Lee de un csv una serie de recomendaciones y devuelve una aleatoria
     * @param tipoUsuario usuario (normal) o psicólogo
     * @return
     */
    public static String getRecomendacion(String tipoUsuario)
    {
        HashMap<Integer, String> hmUsuario = new HashMap<Integer, String>();
        HashMap<Integer, String> hmPsicologo = new HashMap<Integer, String>();
        String recomendacion = "¡Vaya! No se ha encontrado una recomendación para ti hoy";
        try
        {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            InputStream inputStream = IORecomendaciones.class.getClassLoader().getResourceAsStream("recomendaciones.csv");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
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
