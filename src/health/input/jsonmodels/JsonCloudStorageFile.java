package health.input.jsonmodels;

import java.util.List;

public class JsonCloudStorageFile {
	protected String filekey;
	protected String LastModified;
	protected String Size;
	protected String ETag;
	protected String StorageClass;
	protected String link;
	public String getKey() {
		return filekey;
	}
	public void setKey(String key) {
		filekey = key;
	}
	public String getLastModified() {
		return LastModified;
	}
	public void setLastModified(String lastModified) {
		LastModified = lastModified;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public String getETag() {
		return ETag;
	}
	public void setETag(String eTag) {
		ETag = eTag;
	}
	public String getStorageClass() {
		return StorageClass;
	}
	public void setStorageClass(String storageClass) {
		StorageClass = storageClass;
	}
	
	
	
}
