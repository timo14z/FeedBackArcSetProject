package feedbackarcsetproblem;

import java.awt.Dimension;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;


public class Graph_Viz 
{
    int edgeCount;// This works with the inner MyEdge class
    LinkedList<String> distinct_Vertex;//used to enter vertexes
    LinkedList<String> source_Vertex;
    LinkedList<String> target_Vertex;
    LinkedList<Double> edge_Weight;//used to enter edge weight
    LinkedList<String> edge_Label; //used to enter edge levels
    
    LinkedList<String> distinct_Vertex2;//used to enter vertexes
    LinkedList<String> source_Vertex2;
    LinkedList<String> target_Vertex2;
    LinkedList<Double> edge_Weight2;//used to enter edge weight
    LinkedList<String> edge_Label2; //used to enter edge levels
    
    LinkedList<String> distinct_Vertex3;//used to enter vertexes
    LinkedList<String> source_Vertex3;
    LinkedList<String> target_Vertex3;
    LinkedList<Double> edge_Weight3;//used to enter edge weight
    LinkedList<String> edge_Label3; //used to enter edge levels
    
    public Graph_Viz(Prims graph){
        edgeCount = 0;
        distinct_Vertex = new LinkedList<String>();//used to enter vertexes
        source_Vertex = new LinkedList<String>();
        target_Vertex = new LinkedList<String>();
        edge_Weight = new LinkedList<Double>();//used to enter edge weight
        edge_Label = new LinkedList<String>(); //used to enter edge levels
        
        distinct_Vertex2 = new LinkedList<String>();//used to enter vertexes
        source_Vertex2 = new LinkedList<String>();
        target_Vertex2 = new LinkedList<String>();
        edge_Weight2 = new LinkedList<Double>();//used to enter edge weight
        edge_Label2 = new LinkedList<String>(); //used to enter edge levels
        
        for(int i=0;i<graph.getN();++i)
        {
            distinct_Vertex.add("" + i);
            distinct_Vertex2.add("" + i);
        }
        for(int i=0;i<graph.getN();++i)
            for(int j=i + 1;j<graph.getN();++j)
                if(graph.check_undirected_edge(i, j)){
                    source_Vertex.add("" + i);
                    target_Vertex.add("" + j);
                    edge_Weight.add((double)graph.get_weight_edge(i, j));
                    edge_Label.add("" + graph.get_weight_edge(i, j));
                    if(graph.check_mst_edge(i, j))
                    {
                        source_Vertex2.add("" + i);
                        target_Vertex2.add("" + j);
                        edge_Weight2.add((double)graph.get_weight_edge(i, j));
                        edge_Label2.add("" + graph.get_weight_edge(i, j));
                    }
                }
        visualize_Directed_Graph("Full Graph" , 0 ,EdgeType.UNDIRECTED , distinct_Vertex, source_Vertex, target_Vertex, edge_Weight, edge_Label);
        visualize_Directed_Graph("Mst Graph" , 1 , EdgeType.UNDIRECTED ,distinct_Vertex2, source_Vertex2, target_Vertex2, edge_Weight2, edge_Label2);
    } 
    
    
    public Graph_Viz(Fmas_Directed_Graph graph){
        edgeCount = 0;
        distinct_Vertex = new LinkedList<String>();//used to enter vertexes
        source_Vertex = new LinkedList<String>();
        target_Vertex = new LinkedList<String>();
        edge_Weight = new LinkedList<Double>();//used to enter edge weight
        edge_Label = new LinkedList<String>(); //used to enter edge levels
        
        distinct_Vertex2 = new LinkedList<String>();//used to enter vertexes
        source_Vertex2 = new LinkedList<String>();
        target_Vertex2 = new LinkedList<String>();
        edge_Weight2 = new LinkedList<Double>();//used to enter edge weight
        edge_Label2 = new LinkedList<String>(); //used to enter edge levels
        
        distinct_Vertex3 = new LinkedList<String>();//used to enter vertexes
        source_Vertex3 = new LinkedList<String>();
        target_Vertex3 = new LinkedList<String>();
        edge_Weight3 = new LinkedList<Double>();//used to enter edge weight
        edge_Label3 = new LinkedList<String>(); //used to enter edge levels
        
        for(int i=0;i<graph.getN();++i)
        {
            distinct_Vertex.add("" + i);
            distinct_Vertex2.add("" + i);
            distinct_Vertex3.add("" + i);
        }
        for(int i=0;i<graph.getN();++i)
            for(int j=i + 1;j<graph.getN();++j)
                if(graph.check_directed_edge(i, j)){
                    source_Vertex.add("" + i);
                    target_Vertex.add("" + j);
                    edge_Weight.add(0.0);
                    edge_Label.add("");
                    if(graph.check_out1(i, j))
                    {
                        source_Vertex2.add("" + i);
                        target_Vertex2.add("" + j);
                        edge_Weight2.add(0.0);
                        edge_Label2.add("");
                    }
                    if(graph.check_out2(i, j))
                    {
                        source_Vertex3.add("" + i);
                        target_Vertex3.add("" + j);
                        edge_Weight3.add(0.0);
                        edge_Label3.add("");
                    }
                }
        visualize_Directed_Graph("Full Graph" , 0 ,EdgeType.DIRECTED , distinct_Vertex, source_Vertex, target_Vertex, edge_Weight, edge_Label);
        visualize_Directed_Graph("First , Third and Fourth Methods" , 1 , EdgeType.DIRECTED ,distinct_Vertex2, source_Vertex2, target_Vertex2, edge_Weight2, edge_Label2);
        visualize_Directed_Graph("Second Method" , 2 , EdgeType.DIRECTED ,distinct_Vertex3, source_Vertex3, target_Vertex3, edge_Weight3, edge_Label3);

    }
    
//    public Graph_Viz(Fmas_Directed_Graph prims){
//        edgeCount = 0;
//        distinct_Vertex = new LinkedList<String>();//used to enter vertexes
//        source_Vertex = new LinkedList<String>();
//        target_Vertex = new LinkedList<String>();
//        edge_Weight = new LinkedList<Double>();//used to enter edge weight
//        edge_Label = new LinkedList<String>(); //used to enter edge levels
//        
//        distinct_Vertex2 = new LinkedList<String>();//used to enter vertexes
//        source_Vertex2 = new LinkedList<String>();
//        target_Vertex2 = new LinkedList<String>();
//        edge_Weight2 = new LinkedList<Double>();//used to enter edge weight
//        edge_Label2 = new LinkedList<String>(); //used to enter edge levels
//        
//        for(int i=0;i<prims.getN();++i)
//        {
//            distinct_Vertex.add("" + i);
//            distinct_Vertex2.add("" + i);
//        }
//        for(int i=0;i<prims.getN();++i)
//            for(int j=i + 1;j<prims.getN();++j)
//                if(prims.check_undirected_edge(i, j)){
//                    source_Vertex.add("" + i);
//                    target_Vertex.add("" + j);
//                    edge_Weight.add((double)prims.get_weight_edge(i, j));
//                    edge_Label.add("" + prims.get_weight_edge(i, j));
//                    if(prims.check_mst_edge(i, j))
//                    {
//                        source_Vertex2.add("" + i);
//                        target_Vertex2.add("" + j);
//                        edge_Weight2.add((double)prims.get_weight_edge(i, j));
//                        edge_Label2.add("" + prims.get_weight_edge(i, j));
//                    }
//                }
//        visualize_Directed_Graph("Full Graph" , 0 ,EdgeType.UNDIRECTED , distinct_Vertex, source_Vertex, target_Vertex, edge_Weight, edge_Label);
//        visualize_Directed_Graph("Mst Graph" , 1 , EdgeType.UNDIRECTED ,distinct_Vertex2, source_Vertex2, target_Vertex2, edge_Weight2, edge_Label2);
//    } 
        
    class MyNode 
    {
        //static int edgeCount = 0;   // This works with the inner MyEdge class
        String id;
        public MyNode(String id) 
        {
            this.id = id;
        }
        public String toString() 
        {    
            return "V "+id;  
        } 
        public String Node_Property()
        {
            String node_prop = id;
            return(node_prop);
        }
    }

    class MyLink 
    {
        double weight;
        String Label;
        int id;

        public MyLink(double weight, String Label) 
        {
            this.id = edgeCount++;
            this.weight = weight;
            this.Label = Label;
        } 

        public String toString() 
        {
            return "E "+id;
        }
        public String Link_Property()
        {
            String Link_prop = Label;
            return(Link_prop);
        }
        public String Link_Property_wt()
        {
            String Link_prop_wt = ""+weight;
            return(Link_prop_wt);
        }
    }
    //used to construct graph and call graph algorithm used in JUNG
    private void visualize_Directed_Graph(String title ,int pos ,EdgeType edgeType , LinkedList<String> Distinct_nodes, LinkedList<String> source_vertex, LinkedList<String> target_vertex, LinkedList<Double> Edge_Weight, LinkedList<String> Edge_Label)
    {
            //CREATING weighted directed graph
            //Graph<MyNode, MyLink> g = new DirectedSparseGraph<Graph_Viz.MyNode, Graph_Viz.MyLink>();
            Graph<MyNode, MyLink> g = new DirectedSparseGraph<Graph_Viz.MyNode, Graph_Viz.MyLink>();
            if(edgeType == EdgeType.UNDIRECTED)
                g = new UndirectedSparseGraph<Graph_Viz.MyNode, Graph_Viz.MyLink>();
          
            //create node objects
            Hashtable<String, MyNode> Graph_Nodes = new Hashtable<String, Graph_Viz.MyNode>();
            LinkedList<MyNode> Source_Node = new LinkedList<Graph_Viz.MyNode>();
            LinkedList<MyNode> Target_Node = new LinkedList<Graph_Viz.MyNode>();
            LinkedList<MyNode> Graph_Nodes_Only = new LinkedList<Graph_Viz.MyNode>();
            //LinkedList<MyLink> Graph_Links = new LinkedList<Graph_Algos.MyLink>();
            //create graph nodes
            for(int i=0;i<Distinct_nodes.size();i++)
            {
                String node_name = Distinct_nodes.get(i);
                MyNode data = new MyNode(node_name);
                Graph_Nodes.put(node_name, data);
                Graph_Nodes_Only.add(data);
            }
            //Now convert all source and target nodes into objects
            for(int t=0;t<source_vertex.size();t++)
            {
                Source_Node.add(Graph_Nodes.get(source_vertex.get(t)));
                Target_Node.add(Graph_Nodes.get(target_vertex.get(t)));
            }
            //Now add nodes and edges to the graph
            for(int i=0;i<Edge_Weight.size();i++)
            {
                g.addEdge(new MyLink(Edge_Weight.get(i),Edge_Label.get(i)),Source_Node.get(i), Target_Node.get(i), edgeType);
            }

            //-------------

            CircleLayout<MyNode, MyLink> layout1 = new CircleLayout<MyNode,MyLink>(g);
            layout1.setSize(new Dimension(600, 600));
            BasicVisualizationServer<MyNode, MyLink> viz = new BasicVisualizationServer<MyNode,MyLink>(layout1);
            viz.setPreferredSize(new Dimension(600, 600));

            Transformer<MyNode, String> vertexLabelTransformer = new Transformer<MyNode, String>() {
                public String transform(MyNode vertex) {
                    return (String) vertex.Node_Property();
                }
            };

            Transformer<MyLink, String> edgeLabelTransformer = new Transformer<MyLink, String>() {
                public String transform(MyLink edge) {
                    return "[ "+edge.Link_Property()+" ]: Wt = "+edge.Link_Property_wt();
                }
            };

            viz.getRenderContext().setEdgeLabelTransformer(edgeLabelTransformer);
            viz.getRenderContext().setVertexLabelTransformer(vertexLabelTransformer);

            JFrame frame = new JFrame(title);
            frame.setBounds(pos * 600, 0, pos * 600 , 0);
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(viz);
            frame.pack();
            frame.setVisible(true);

    }
}