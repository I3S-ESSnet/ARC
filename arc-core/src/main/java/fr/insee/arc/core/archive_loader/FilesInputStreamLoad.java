package fr.insee.arc.core.archive_loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;

import fr.insee.arc.utils.utils.LoggerDispatcher;

/**
 * Just a map with inputstream in it. We have to read throught the file multiple time, this object is here to
 * carry all the necessary inputstram.
 * @author S4LWO8
 *
 */
public class FilesInputStreamLoad {
    private static final Logger LOGGER = Logger.getLogger(ZipDecompressor.class);

    private File theFileToRead;
    
    private Map<FilesInputStreamLoadKeys, InputStream> mapInputStream = new EnumMap<>(FilesInputStreamLoadKeys.class); 
    
    
    public FilesInputStreamLoad(File theFileToRead, FilesInputStreamLoadKeys... keys) throws Exception {
	super();
	try {
	   if (keys != null) {
	    for (FilesInputStreamLoadKeys aKey : keys) {
		mapInputStream.put(aKey, new GZIPInputStream(new FileInputStream(theFileToRead)));
	    }
	}
	} catch (FileNotFoundException e) {
	    LoggerDispatcher.error("Can't instanciate FilesInputStreamLoad for file " + theFileToRead.getName(), LOGGER);
	    throw e;
	}
	this.theFileToRead = theFileToRead;
    }
    
    
    public FilesInputStreamLoad() {
	super();
    }
    
    public void closeAll() throws IOException {
	 try {	    
	    for (Map.Entry<FilesInputStreamLoadKeys,InputStream> entry : this.mapInputStream.entrySet()) {
		entry.getValue().close();
	    }
	} catch (IOException e) {
	    LoggerDispatcher.error("Can't closeAll FilesInputStreamLoad for file " + theFileToRead.getName(), LOGGER);
	    throw e;
	}
    }
    
    public InputStream getTmpInxLoad() {
        return mapInputStream.get(FilesInputStreamLoadKeys.LOAD);
    }

    public InputStream getTmpInxIdentify() {
	return mapInputStream.get(FilesInputStreamLoadKeys.IDENTIFICATION);
    }
  
    public InputStream getTmpInxCSV() {
	return mapInputStream.get(FilesInputStreamLoadKeys.CSV);
    }
   
    public File getTheFileToRead() {
        return theFileToRead;
    }
    public void setTheFileToRead(File theFileToRead) {
        this.theFileToRead = theFileToRead;
    }

    public Map<FilesInputStreamLoadKeys, InputStream> getMapInputStream() {
        return mapInputStream;
    }

    public void setMapInputStream(Map<FilesInputStreamLoadKeys, InputStream> mapInputStream) {
        this.mapInputStream = mapInputStream;
    }
    
    
}
