package edu.usm.cos420.iteration1.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.usm.cos420.iteration1.exceptions.DAOException;
import edu.usm.cos420.iteration1.util.AppendingObjectOutputStream;

public class ObjectStreamDao<IDType, T extends Serializable> implements GenericDao<IDType, T>  {

	private Map<IDType,T> entityMap = new HashMap<IDType, T>();

	private ObjectOutputStream oos;

	private String filename;

	private int objectCount;


	public ObjectStreamDao(){
		this("unknown.ser");
	}



	public ObjectStreamDao(String filename){
		this.filename = filename;
		File file = new File(filename);

		try{
			if(!file.exists()){
				FileOutputStream fos = new FileOutputStream(filename);
				oos = new ObjectOutputStream(fos);
			}else{
				FileOutputStream fos = new FileOutputStream(file, true);
				oos = new AppendingObjectOutputStream(fos);
				objectCount = determineNumberOfObjectsInStream();
			}
		}
		catch (FileNotFoundException e) {
			System.err.println("Could not find "+ filename + e);   	
		}
		catch (IOException e) {
			System.err.println("Some Error writing to stream " + e);   	
		}
		catch (Exception e){
			System.err.println(e);
		}	
	}
	public void add(IDType id, T entity) {
		try{
			oos.writeObject(id);
			oos.writeObject(entity);
			objectCount++;
		}
		catch (FileNotFoundException e){
			System.err.println("Could not find " + filename + " " + e);	
		}
		catch (IOException e){
			System.err.println("Some error writing to stream " + e);
		}
		catch (Exception e){
			System.err.println(e);
		}

	}

	@Override
	public void update(IDType id, T entity) {
		entityMap = readStreamIntoMap();
		if(entityMap.get(id) == null)
			throw new DAOException("");
		entityMap.put(id, entity);
		writeMapIntoStream(entityMap);

	}


	public void remove(IDType id) {
		entityMap = readStreamIntoMap();
		entityMap.remove(id);
		writeMapIntoStream(entityMap);
		objectCount--;

	}

	public T find(IDType key) {
		// TODO Auto-generated method stub
		entityMap = readStreamIntoMap();
		if(entityMap.isEmpty()){
			return null;
		}
		return entityMap.get(key);

	}

	public List<T> list() {
		entityMap = readStreamIntoMap();
		return new ArrayList<T> (entityMap.values());
	}

	public int determineNumberOfObjectsInStream(){
		int tmpCount = 0;
		File file = new File(filename);
		if(!file.exists()){
			return 0;
		}
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (fis.available() > 0)
			{
				IDType id = (IDType) ois.readObject();
				T entity = (T) ois.readObject();
				tmpCount++;
			}
			ois.close();
		}   		
		catch (FileNotFoundException e) {
			System.err.println("Could not find " + filename + "  "+ e);   	
		}
		catch (EOFException e) {
		}
		catch (IOException e) {
			System.err.println("Some Error writing to stream " + e);   	
		}
		catch (Exception e){
			System.err.println(e);
		}	

		return tmpCount; 
	}

	private Map<IDType, T> readStreamIntoMap() {
		entityMap.clear();

		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			for (int i = 0;i<objectCount;i++)
			{
				IDType id = (IDType) ois.readObject();
				T entity = (T) ois.readObject();
				entityMap.put(id, entity);	
			}
			ois.close();
		}   		
		catch (FileNotFoundException e) {
			System.err.println("Could not find " + filename + "  "+ e);   	
		}
		catch (EOFException e) {
		}
		catch (IOException e) {
			System.err.println("Some Error writing to stream " + e);   	
		}
		catch (Exception e){
			System.err.println(e);
		}	

		return entityMap;    		                     
	}

	// utility routine to write the entire object stream into a map

	private void writeMapIntoStream(Map<IDType, T> map) 
	{
		try {
			oos.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("Could not find test.ser " + e);   	
		}
		catch (IOException e) {
			System.err.println("Some Error writing to stream " + e);   	
		}
		catch (Exception e){
			System.err.println(e);
		}	

		try {
			FileOutputStream fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			for (Map.Entry<IDType, T> entry : map.entrySet())
			{
				oos.writeObject(entry.getKey());
				oos.writeObject(entry.getValue());
			}   
			oos.close();
		}   		
		catch (FileNotFoundException e) {
			System.err.println("Could not find " + filename + "  "+ e);   	
		}
		catch (IOException e) {
			System.err.println("Some Error writing to stream " + e);   	
		}
		catch (Exception e){
			System.err.println(e);
		}	
	}

}
