package edu.sjsu.cmpe275.cusr.model;
import java.util.Stack;

public class StationGraph {
	private int totalvertex;
	private int current_count;
	private StationVertex vertexList[];
	private int AdjMat[][];
	private Stack<Integer> stack;


// public StationGraph(int max_vertex)
// {
// 	this.current_count =0;
// 	this.totalvertex = max_vertex;
// 	this.AdjMat = new int [this.totalvertex][this.totalvertex];
// 	this.vertexList = new StationVertex[this.totalvertex];
	
// 	for(int i=0;i<AdjMat.length;i++)
// 	{
// 		for(int j=0;j<AdjMat[i].length;j++)
// 		{
// 			AdjMat[i][j]=0;
// 		}
// 	}
// 	stack =  new Stack<Integer>();
// }
// public  void addVertex()
// {
// 	vertexList[current_count++] = new StationVertex();
// }
// public void addEdge(int start ,int end)
// {
// 	AdjMat[start][end]=1;
// 	AdjMat[end][start]=1;
// }
// public void dfs()
// {
// 	vertexList[0].IsVisited = true; // mark it
// 	displayVertex(0); // display it
// 	stack.push(0); // push it
// 	while( !stack.isEmpty() ) // until stack empty,
// 	{
// 	// get an unvisited vertex adjacent to stack top
// 	int v = getAdjUnvisitedVertex( stack.peek() );
// 	if(v == -1) // if no such vertex,
// 	stack.pop();
// 	else // if it exists,
// 	{
// 	vertexList[v].IsVisited = true; // mark it
// 	displayVertex(v); // display it
// 	stack.push(v); // push it
// 	}
// 	} // end while
// 	// stack is empty, so we’re done
// 	for(int j=0; j<current_count; j++) // reset flags
// 	vertexList[j].IsVisited = false;
// }

// public int getAdjUnvisitedVertex(int v)
// {
// for(int i=0;i<this.totalvertex;i++)
// {
// 	if(this.AdjMat[v][i]== 5 && this.vertexList[i].IsVisited == false)
// 	{
// 		return i;
// 	}	
// }
// return -1;
// }


}