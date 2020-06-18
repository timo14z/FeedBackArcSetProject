package feedbackarcsetproblem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javafx.util.Pair;

public class Fmas_Directed_Graph {

    private boolean adj[][];
    private boolean isBackEdge[][];
    private boolean output1[][];
    private boolean output2[][];
    private boolean output4[][];
    private int status[];
    private int order[];
    private int outDegree[];
    private boolean inStack[];
    private int timer;
    private int N;
    
    public int getN(){
        return N;
    }

    public Fmas_Directed_Graph(int n) {
        this.N = n;
        this.timer = 0;
        adj = new boolean[N][N];
        output1 = new boolean[N][N];
        output2 = new boolean[N][N];
        output4 = new boolean[N][N];
        status = new int[N];
        isBackEdge = new boolean[N][N];
        outDegree = new int[N];
        inStack = new boolean[N];
        order = new int[N];
        for (int i = 0; i < N; i++) {
            status[i] = 0;
            for (int j = 0; j < N; j++)
                adj[i][j] = isBackEdge[i][j] = false;
        }
    }

    public void generateDirected(int sz) {
        for(int i=0;i<N;++i)
            for(int j=0;j<N;++j)
                adj[i][j] = false;
        Random rand = new Random();
        for(int i=0;i<sz;++i)
            for(int j=0;j<sz;++j)
            {
                if(i == j)
                    continue;
                int tmp = rand.nextInt(50);
                boolean b = (tmp % 2 == 0);
                adj[i][j] = b;
            }
    }

    public int edgesCount(){
        int ret = 0;
        for(int i=0;i<N;++i)
            for(int j=0;j<N;++j)
                if(adj[i][j])
                    ret++;
        return ret;
    }

    public void addEdge(int u , int v){
        adj[u][v] = true;
    }

    private void dfs(int curV) {
        status[curV] = 1;//active
        order[curV] = timer++;
        for(int i=0;i<N;i++)
        {
           // if(!adj[curV][i] || status[i] == 2)
               // continue;
            if(status[i] == 0)
            {
                dfs(i);
              //  continue;
            }
            if(status[i] == 1)
                isBackEdge[curV][i] = true;
        }
        status[curV] = 2;//finished
    }
    
    public boolean isBackEdge(int i , int j) {
        if(j > i)
            return false;
        return true;
    }
    
    public int removeBackEdges1() {
        int c1 , c2;
        c1 = c2 = 0;
        for(int i=0;i<N;++i)
            for(int j=0;j<N;++j)
                if(adj[i][j]){
                    if (isBackEdge(i, j))
                        c1++;
                    else
                        c2++;
                }
        
        for(int i=0;i<N;++i)
            for(int j=0;j<N;++j)
                if(adj[i][j]){
                    if (c1 < c2)
                        output1[i][j] = true;
                    else
                        output1[j][i] = true;
                }

        return Math.min(c1 , c2);
        /*if(c1 > c2)
            return false;
        return true;*/
    }

    private int getMaxIndex() {
        int mxi = 0;
        for(int i=1;i<this.N;++i)
            if(!inStack[i] && outDegree[i] > outDegree[mxi])
                mxi = i;
        return mxi;
    }

    public int removeBackEdges2() {
        for(int i=0;i<N;++i)
        {
            outDegree[i] = 0;
            inStack[i] = false;
        }
        for(int i=0;i<N;++i)
            for(int j=0;j<N;++j)
                if(adj[i][j])
                    outDegree[i]++;
        Stack<Integer> st = new Stack<Integer>();
        for(int t=0;t<N;++t)
        {
            int cur = getMaxIndex();
            st.push(cur);
            inStack[cur] = true;
        }

        int s1 , s2;
        s1 = s2 = 0;

        while(!st.empty())
        {
            int x = st.pop();
            for(int i=0;i<N;++i)
            {
                if(adj[x][i])
                {
                    if(!inStack[i])
                    {
                        s1++;
                        output2[x][i] = true;
                    }
                    else
                        s2++;
                }
                inStack[x] = false;
            }
        }
        
        if(s2 < s1)
        {
            for(int i=0;i<N;++i)
                for(int j=0;j<N;++j)
                    if(adj[i][j])
                        output2[i][j] = !output2[i][j];
        }
   
        return Math.min(s1 , s2);
    }

    public ArrayList<Pair<Integer , Integer> > getBackEdges() {
        ArrayList<Pair<Integer , Integer> > r1 = new ArrayList<>();
        ArrayList<Pair<Integer , Integer> > r2 = new ArrayList<>();
        for(int i=0;i<N;++i)
            for(int j=0;j<N;++j)
                if(adj[i][j])
                {
                    if(i > j)
                        r1.add(new Pair<>(i , j));
                    else
                        r2.add(new Pair<>(i , j));
                }
        if(r1.size() < r2.size())
            return r1;
        return r2;
    }

    public int removeBackEdges3() {
        ArrayList<Pair<Integer , Integer> > backEdges = getBackEdges();
        return backEdges.size();
        /*for(int i=0;i<backEdges.size();++i)
            adj[backEdges.get(i).getKey()][backEdges.get(i).getValue()] = false;*/
    }

    public int removeBackEdges4() {
        runDFS();
        int cnt1 , cnt2;
        cnt1 = cnt2 = 0;
        for(int i=0;i<N;++i)
            for(int j=0;j<N;++j)
                if(adj[i][j])
                {
                    if (order[j] > order[i])
                        cnt1++;
                    else if (order[i] > order[j])
                        cnt2++;
                }
        return Math.min(cnt1 , cnt2);
    }

    public void runDFS() {
        for(int i=0;i<N;++i)
            if(status[i] == 0)
                dfs(i);
    }

    public void print() {
        for(int i=0;i<N;++i)
            for(int j=0;j<N;++j)
                if(adj[i][j])
                    System.out.println(i + " " + j + " " + isBackEdge[i][j]);
    }

    boolean check_directed_edge(int i, int j) {
        return adj[i][j];
    }

    boolean check_out1(int i, int j) {
        return output1[i][j];
    }

    boolean check_out2(int i, int j) {
        return output2[i][j];
    }

}
