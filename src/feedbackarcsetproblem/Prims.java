/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedbackarcsetproblem;

import java.util.*;
import java.io.*;
/**
 *
 * @author Asus
 */

class Pair {
    int x;
    int y;

    // Constructor
    public Pair(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

class Comp implements Comparator<Pair> {
    @Override
    public int compare(Pair o1, Pair o2) {
        return o2.x - o1.x;
    }
}

public class Prims {
    private int n;
    private int edges[][];
    private int mstGraph[][];
    
    public int getN(){
        return n;
    }

    public Prims(int n) {
        this.n = n;
        edges=new int[this.n][this.n];
        mstGraph=new int[this.n][this.n];
    }
    //to add an undirected edge (t,k) and the weight of this edge =edge_weight
    public void addEdge(int t, int k,int edge_weight)
    {
        edges[t][k]=edge_weight;
        edges[k][t]=edge_weight;
    }
    private void init_mstGraph()
    {
        for(int k=0;k<n;k++)
            for(int j=0;j<n;j++)
                mstGraph[k][j]=0;
    }

    public int edgesCount(){
        int ret = 0;
        for(int i=0;i<n;++i)
            for(int j=i+1;j<n;++j)
                if(edges[i][j] > 0)
                    ret++;
        return ret;
    }

    public boolean check_undirected_edge(int v,int k)
    {
        if(edges[v][k]>0)
            return true;
        return false;
    }
    
    public boolean check_mst_edge(int v , int k){
        return mstGraph[v][k] > 0;
    }
    
    public int get_weight_edge(int i, int v)
    {
        return edges[i][v];
    }
    public void max_sanning_tree( int start){

        boolean q[]=new boolean [n];
        q[start]=true;
        int sz=0;

        while(sz!=n-1){

            int w=Integer.MIN_VALUE;
            int d=-1;
            int c=-1;
            q[start]=true;

            for(int j=0;j<n;j++)
                for (int v=0;v<n;v++)
                    if(check_undirected_edge(j,v))
                        if(q[j]!=q[v] && get_weight_edge(j,v)>w)
                        {
                            c=v;
                            w=edges[j][v];
                            d = j;
                        }
            mstGraph[d][c]=w;
            mstGraph[c][d]=w;

            //      System.out.println(c+" "+d+" final");
            q[d]=true;
            q[c]=true;
            sz++;
        }
    }

    public void max_sanning_tree_key_parent(int start) {

        int key[] = new int[n];
        int par[] = new int[n];
        boolean in[] = new boolean [n];
        key[start] = 0;

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<Pair>(new Comp());

        priorityQueue.add(new Pair(0, start));

        while (!priorityQueue.isEmpty()) {
            Pair top = priorityQueue.poll();
            int u = top.y;
            if(!in[u]) {
                in[u] = true;
                if (par[u] != -1)
                    mstGraph[u][par[u]] = mstGraph[par[u]][u] = top.x;
                for (int v = 0; v < n; v++)
                    if (check_undirected_edge(u, v) && !in[v] && get_weight_edge(u, v) > key[v]) {
                        key[v] = get_weight_edge(u, v);
                        par[v] = u;
                        priorityQueue.add(new Pair(key[v], v));
                    }
            }
        }
    }

    public void generateUnDirected(int sz) {
        for(int i=0;i<n;++i)
            for(int j=0;j<n;++j)
                edges[i][j] = 0;
        Random rand = new Random();
        for(int i=0;i<sz;++i)
            for(int j=i + 1;j<sz;++j)
            {
                int tmp = rand.nextInt(50);
                int b = tmp % 2;
                edges[i][j] = edges[j][i] = b * tmp;
            }
    }

    public void print_max_st(){
        for(int k=0;k<n;k++)
            for(int j=k;j<n;j++)
                if(mstGraph[k][j]>0)
                    System.out.println(k+" "+j);

    }
}