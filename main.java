





  // facebook_social_network takes so much time like 30-40 min

  // since it takes so much time, wanted to add my output  for facebook social_network betweenness : 223  75352

  //                                                          facebook  social_network closeness  :  223  5.8207650454306455E-11


public class main {
    public static void main(String[] args) {

        fileOperation fRead = new fileOperation();

        System.out.println("2016510054 Oral Denizhan Misirlioglu");

        int size = fRead.graphSize("karate_club_network.txt") ;  // reading karate club graph size from txt

        Graph karate_club_network = new Graph(size);

        fRead.txtToGraph(karate_club_network,"karate_club_network.txt");

        karate_club_network.betweennesCloseness(size,"Zachary Karate Club Network"); // betweenness closeness for  karate club

        int size2 = fRead.graphSize("facebook_social_network.txt"); // reading facebook graph size from txt

        Graph facebook_social_network = new Graph(size2);

        fRead.txtToGraph(facebook_social_network,"facebook_social_network.txt"); // creating facebook graph by reading txt

      //  facebook_social_network.betweennesCloseness(size2,"Facebook Social Network"); // betweenness closeness for facebook





    }
}
