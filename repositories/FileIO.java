package repositories;
import java.io.*;

public class FileIO {

    public void writeFile(String[] data, String fileName) {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);

            for (String str : data) {
                if (str != null) {
                    fileWriter.write(str);
                    fileWriter.flush();
                }
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] readFile(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String[] data = new String[100];
            String str;
            int i = 0;
            while ((str = bufferedReader.readLine()) != null && i < data.length) {
                data[i] = str;
                i++;
            }

            bufferedReader.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
