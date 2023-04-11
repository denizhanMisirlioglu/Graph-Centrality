import java.util.*; // This library used for finding the key with the max value in hashmap , no  library used for graphs
                    // also  linkedlist , hashmap and queue libraries are used.

//  this matrix representation graph class got inspired from our lab documents

public class Graph {
    private ArrayList<String> vertices; // to keep vertex names
    private int[][] adjacency; // to keep edges
    private int size;

    public Graph(int size) {
        vertices = new ArrayList<String>();
        adjacency = new int[size][size];
        this.size = size;
    }

    public void addEdge(String source, String destination, int weight) {

        if (!vertices.contains(source))
            vertices.add(source);
        if (!vertices.contains(destination))
            vertices.add(destination);

        int source_index = vertices.indexOf(source);
        int destination_index = vertices.indexOf(destination);
        adjacency[source_index][destination_index] = weight;
        adjacency[destination_index][source_index] = weight;
    }

    public void betweennesCloseness (int size,String networkName){ // function calls  ShrtPathDist function for every pair to calculate  paths and distances
        double distance ;
        double distanceUpdatedSrc; // distance  A->B store purposes
        double distanceUpdatedDest; // distance B->A store purposes since there is only A->B operation done so for B->A B vertice needs to store same distance value.

        HashMap<Integer,Double> closeness = new HashMap(); // Hashmap to store distances for vertice to other vertices like A-B , A-C ... A- n ( sum of distances stored as value)
        HashMap<Integer,Integer> betweenness = new HashMap<>(); // Hashmap to store betweenness value of every vertice

        for(int i = 0 ;i<vertices.size();i++){
            for(int j = 0; j<vertices.size();j++){
                if(i!=j && i<j){ //  upper triangle matrix search no need to  pair path finding twice, A-B is enough no need to B-A since paths are same
                    distance = ShrtPathDistance(vertices.get(i), vertices.get(j),size,betweenness);

                    if(closeness.get(i)==null){   // adding distances to closeness hashmap for pair shortestpath searches
                        distanceUpdatedSrc=distance;

                    }else {
                        distanceUpdatedSrc = closeness.get(i) + distance;

                    }
                    closeness.put(i, distanceUpdatedSrc);
                    if(closeness.get(j)==null){
                        distanceUpdatedDest=distance;

                    }else {
                        distanceUpdatedDest = closeness.get(j) + distance;

                    }
                    closeness.put(j, distanceUpdatedDest);

                }
            }
        }

        int maxValueInMap=(Collections.max(betweenness.values()));  // This will return max value in betweenness  Hashmap

        for (Map.Entry<Integer, Integer> entry : betweenness.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                System.out.println(networkName+"- The Highest Node for Betweennes and the value :"+vertices.get(entry.getKey()) +"  "+ betweenness.get(entry.getKey()) );

            }
        }

        double minValueInMap=(Collections.min(closeness.values())); // This will return min value in closeness Hashmap

        for (Map.Entry<Integer, Double> entry : closeness.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==minValueInMap) {
                double closenessValue;
                closenessValue = 1/closeness.get(entry.getKey()); // find min value at closeness hashmap and calculate as 1 / value then print since its the biggest cause 1 / min > others
                System.out.println(networkName+"- The Highest Node for Closeness and the value :"+vertices.get(entry.getKey()) +"  "+closenessValue );

            }
        }
    }

    public double ShrtPathDistance( String src, String dest , int size , HashMap<Integer,Integer> betweenness) // calculate each shortest path between SRC -> DEST also return distance
    {
        double pairDistance = 0;
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int ata[] = new int[size];
        int dist[] = new int[size];

        if (!BFS( src, dest, size, ata, dist)) {

             pairDistance = Integer.MAX_VALUE; // if not connected  distance is  infinite
              return pairDistance;
        }
        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = vertices.indexOf(dest);
        path.add(crawl);
        while (ata[crawl] != -1) {
            path.add(ata[crawl]);
            crawl = ata[crawl];
        }

        int countUpdated;
        for (int i = path.size() - 1; i >= 0; i--) { // adding shortest path vertice's counts of  source to dest to betweenness hashmap ,

            if(betweenness.get(path.get(i))==null){
                countUpdated=  1;

            }else {
                countUpdated = betweenness.get(path.get(i)) + 1;
            }
            betweenness.put(path.get(i),countUpdated);
        }
        pairDistance = dist[vertices.indexOf(dest)] ; // distance between source to destination
        return pairDistance;
    }

    public boolean BFS(String src , String dest ,int size ,int ata [], int dist [] ) {
        Queue<Integer> queue = new LinkedList<>();
        int root = vertices.indexOf(src);

        queue.add(root);
        int[] visited = new int[size];
        visited[root] = 1;

        for (int i = 0; i < size; i++) {
            dist[i] = Integer.MAX_VALUE;
            ata[i] = -1;
        }
        dist[root] = 0;

        while (!queue.isEmpty()) {
            int current_vertex = queue.poll(); // the top element is selected and removed from queue

            int v;
            while ((v = unvisitedNeighbor(current_vertex, visited)) != -1) {
                queue.add(v);
                visited[v] = 1;
                dist[v] = dist[current_vertex] + 1;
                ata[v] = current_vertex;

                 if (v == vertices.indexOf(dest))
                     return true;
            }
        }
        return false;
    }

    public int unvisitedNeighbor(int index, int[] visited) {
        for (int i = 0; i < adjacency.length; i++) {
            if (adjacency[index][i] != 0 && visited[i] == 0)
                return i;
        }
        return -1;
    }
}
