
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class fileOperation {

    public void txtToGraph(Graph graph , String txt){  // create Graph by reading txt

        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(txt));

            while((line = br.readLine()) != null) {
                String[] txtArray = line.split(" ");
                graph.addEdge(txtArray[0],txtArray[1],1);
            }
            br.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int graphSize (String txt) {  // find Size of the graph by reading txt
        int graphSize = 0;
        ArrayList<String> vertexNames = new ArrayList<String>( ); // to keep vertex names
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(txt));

            while((line = br.readLine()) != null) {
                String[] txtArray = line.split(" ");
                if(!vertexNames.contains(txtArray[0])) {
                    vertexNames.add(txtArray[0]);
                }
                if(!vertexNames.contains(txtArray[1])){
                    vertexNames.add(txtArray[1]);
                }
            }
            br.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
        graphSize = vertexNames.size();


        return graphSize ;
    }

}
