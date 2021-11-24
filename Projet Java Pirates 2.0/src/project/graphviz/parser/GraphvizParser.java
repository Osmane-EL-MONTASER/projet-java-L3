package project.graphviz.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import project.crew.Crew;
import project.crew.Pirate;
import project.crew.Treasure;
import project.crew.graph.PirateVertex;
import project.graph.Edge;
import project.graph.Graph;
import project.graph.Vertex;
import project.graph.exceptions.EdgeDuplicateException;
import project.graph.exceptions.VertexNotFoundException;

/**
 * Un parser pour transformer une classe Graph en
 * JSON et et recevoir le graphe en tant que PNG.
 * 
 * Cela permet d'éviter de faire installer DOT
 * en même temps que le logiciel, mais l'utilisation
 * du logiciel nécessite une connexion Internet.
 * 
 * @author EL MONTASER Osmane
 * @version 1.0
 * @since 3.0
 */
public class GraphvizParser {
	
	public static String parseGraph(Graph<?> graph) {
		String str = new String();
		
		str += "{\"graph\": \"graph G{bgcolor=transparent{node[margin=0 fontcolor=black"
				+ " fontsize=12 width=0.5 shape=circle "
				+ "style=filled fillcolor=white]";
		
		for(Vertex<?> v : graph.getVertices())
			str += parseVertex(v) + " ";
		
		for(Edge<?> e : graph.getEdges())
			str += parseEdge(e) + " ";
		
		str += "}}\",\"format\": \"png\"}"; 
		
		return str;
	}
	
	public static String parsePirateGraph(Graph<?> graph, Crew c) {
		String str = new String();
		
		str += "{\"graph\": \"graph G{bgcolor=transparent{node[margin=0 fontcolor=black"
				+ " fontsize=12 width=0.5 shape=circle "
				+ "style=filled fillcolor=white]";
		
		for(Vertex<?> v : graph.getVertices())
			str += parsePirateVertex(v, c.getAttributions(), c.getJealousPirates()) + " ";
		
		for(Edge<?> e : graph.getEdges())
			str += parseEdge(e) + " ";
		
		str += "}}\",\"format\": \"png\"}"; 
		
		return str;
	}
	
	public static String parseGraphWithSelected(Graph<?> graph, int i, int j) {
		String str = new String();
		
		str += "{\"graph\": \"graph G{bgcolor=transparent{node[margin=0 fontcolor=black"
				+ " fontsize=12 width=0.5 shape=circle "
				+ "style=filled fillcolor=white]";
		
		int k = 0;
		for(Vertex<?> v : graph.getVertices()) {
			if(k == j && j != -1)
				str += parseSelectedVertex(v) + " ";
			else if(k == i)
				str += parseSelectedVertex(v) + " ";
			else
				str += parseVertex(v) + " ";
			k++;
		}
		
		for(Edge<?> e : graph.getEdges())
			str += parseEdge(e) + " ";
		
		str += "}}\",\"format\": \"png\"}"; 
		
		return str;
	}
	
	private static String parseVertex(Vertex<?> v) {
		String str = new String();
		
		str += v.getId() + "[fixedsize=true width=2 label=\\\"" + v.getLabel() + "\\\"]";
		
		return str;
	}
	
	private static String parsePirateVertex(Vertex<?> v, LinkedHashMap<PirateVertex, Treasure> attributions
			, ArrayList<Vertex<Pirate>> jealousPirates) {
		String str = new String();
		String attribution = attributions.get(v) != null ? "\\n\\n" + attributions.get(v).toString() : "";
		String coloring = jealousPirates.contains(v) ? "fillcolor=red" : "fillcolor=green";
		
		str += v.getId() + "[fixedsize=true " + coloring + " width=2 label=\\\"" + v.getLabel() + attribution + "\\\"]";
		
		return str;
	}
	
	private static String parseSelectedVertex(Vertex<?> v) {
		String str = new String();
		
		str += v.getId() + "[fixedsize=true width=2 fillcolor=blue label=\\\"" + v.getLabel() + "\\\"]";
		
		return str;
	}
	
	private static String parseEdge(Edge<?> v) {
		String str = new String();
		
		str += v.getV1().getId() + " -- " + v.getV2().getId() + "[color=white] ";
		
		return str;
	}
	
	public static void savePirateGraphToPng(Graph<?> g, Crew c) throws IOException {
		URL url = new URL ("https://quickchart.io/graphviz");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);

		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = parsePirateGraph(g, c).getBytes("utf-8");
		    os.write(input, 0, input.length);			
		    os.close();
		}

		BufferedReader reader = null;
		FileOutputStream fos = null;
		try {
			InputStream in = con.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
 
			fos = new FileOutputStream(new File("graph.png"));
			byte[] buff = new byte[1024];
			int l = in.read(buff);
			while (l > 0) {
				fos.write(buff, 0, l);
				l = in.read(buff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveGraphToPng(Graph<?> g) throws IOException {
		URL url = new URL ("https://quickchart.io/graphviz");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);

		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = parseGraph(g).getBytes("utf-8");
		    os.write(input, 0, input.length);			
		    os.close();
		}

		BufferedReader reader = null;
		FileOutputStream fos = null;
		try {
			InputStream in = con.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
 
			fos = new FileOutputStream(new File("graph.png"));
			byte[] buff = new byte[1024];
			int l = in.read(buff);
			while (l > 0) {
				fos.write(buff, 0, l);
				l = in.read(buff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveGraphToPngWithSelected(Graph<?> g, int i, int j) throws IOException {
		URL url = new URL ("https://quickchart.io/graphviz");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);

		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = parseGraphWithSelected(g, i, j).getBytes("utf-8");
		    os.write(input, 0, input.length);			
		    os.close();
		}

		BufferedReader reader = null;
		FileOutputStream fos = null;
		try {
			InputStream in = con.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
 
			fos = new FileOutputStream(new File("graph.png"));
			byte[] buff = new byte[1024];
			int l = in.read(buff);
			while (l > 0) {
				fos.write(buff, 0, l);
				l = in.read(buff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveGraphToPngWithSelected(Graph<?> g, int i) throws IOException {
		URL url = new URL ("https://quickchart.io/graphviz");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);

		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = parseGraphWithSelected(g, i, -1).getBytes("utf-8");
		    os.write(input, 0, input.length);			
		    os.close();
		}

		BufferedReader reader = null;
		FileOutputStream fos = null;
		try {
			InputStream in = con.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
 
			fos = new FileOutputStream(new File("graph.png"));
			byte[] buff = new byte[1024];
			int l = in.read(buff);
			while (l > 0) {
				fos.write(buff, 0, l);
				l = in.read(buff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) throws IllegalArgumentException, EdgeDuplicateException, IOException, VertexNotFoundException {
		Crew c = new Crew();
		c.addCrewMember(new Pirate("Edward Teach", null));
		c.addCrewMember(new Pirate("Oliver Levasseur", null));
		
		c.addBadRelations(0, 1);
		
		saveGraphToPng(c.getGraph());	
	}
}
