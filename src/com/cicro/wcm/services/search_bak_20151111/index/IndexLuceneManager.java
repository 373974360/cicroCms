package com.cicro.wcm.services.search_bak_20151111.index;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.SimpleFSDirectory;

import com.cicro.analyzer.lucene.IKAnalyzer;
import com.cicro.wcm.services.search.SearchForInterface;

public class IndexLuceneManager {

    // 保存索引文件的地方
    static String indexDir = SearchForInterface.getIndexPathRoot();
    static Directory dir = null;
    static boolean isInit = true;
    
    static {

    }
    
    public static IndexWriter initIndexWriter() throws CorruptIndexException, LockObtainFailedException, IOException {
    	if (dir == null) {
            // 创建Directory对象
            dir = new SimpleFSDirectory(new File(indexDir));
            File file = new File(indexDir);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    	IndexWriter indexWriter = null;
        try{
	        indexWriter = new IndexWriter(dir, new IKAnalyzer(), isInit, IndexWriter.MaxFieldLength.UNLIMITED);
	        indexWriter.setMergeFactor(20);//参数值较小的时候，创建索引的速度较慢。当参数值较大的时候，创建索引的速度就比较快。大于10适合批量创建索引
	        indexWriter.setRAMBufferSizeMB(500);//该方法可以设置更新文档使用的内存达到指定大小之后才写入硬盘，这样可以提高写索引的速度，尤其是在批量建索引的时候
	        isInit = false;
        }catch(Exception e){
        	e.printStackTrace();
        }
        return indexWriter;
    }

    //关闭IndexWriter
    public static void closeIndexWriter(IndexWriter indexWriter) {
        try {
            if (indexWriter != null) {
            	indexWriter.commit();
                indexWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }


    /*
    * Field.Store.COMPRESS:压缩保存,用于长文本或二进制数据
    * Field.Store.YES:保存
    * Field.Store.NO:不保存
    * 
    * Field.Index.NO:不建立索引 
    * Field.Index.TOKENIZED:分词,建索引
    * Field.Index.UN_TOKENIZED:不分词,建索引
    */
    //写索引
    public static void AddDocument(IndexWriter indexWriter, Document doc) {
        try {
            indexWriter.addDocument(doc);
            //indexWriter.optimize();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    //删索引
    public static void DeleteDocument(IndexWriter indexWriter, Term term) {
        try {
            indexWriter.deleteDocuments(term);
        } catch (Exception e) {
            e.printStackTrace();
            closeIndexWriter(indexWriter);
        } 
    }


    public static void main(String arr[]) {

    }
}
