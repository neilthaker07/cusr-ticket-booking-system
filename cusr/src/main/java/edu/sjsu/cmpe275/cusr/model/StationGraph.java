package edu.sjsu.cmpe275.cusr.model;

public class StationGraph {
	private int totalvertex;
	private StationVertex vertexList[];
	private int AdjMat[][];


public StationGraph(int max_vertex)
{
	
	this.totalvertex = max_vertex;
	this.AdjMat = new int [this.totalvertex][this.totalvertex];
	this.vertexList = new StationVertex[this.totalvertex];
	
	for(int i=0;i<AdjMat.length;i++)
	{
		for(int j=0;j<AdjMat[i].length;j++)
		{
			if(j==i+1 || j%5==0)
			{
				this.AdjMat[i][j]=1;
			}
			else
			{
				this.AdjMat[i][j]=0;
			}
		}
		this.vertexList[i]=new StationVertex();
	}
}


public int getUnivisitedVertex(int v)
{
for(int i=0;i<this.totalvertex;i++)
{
	if(this.AdjMat[v][i]== 5 && this.vertexList[i].IsVisited == false)
	{
		return i;
	}	
}
return -1;
}


}