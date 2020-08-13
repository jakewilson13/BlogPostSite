package com.techtalentsouth.TechTalentBlog.BlogPost;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BlogPost {

	@Id //sets property 'id as unique key in database
	//allows the id to be generated
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;
	private String blogEntry;
	
	
	
	public BlogPost() {
		//non-argument constructor for @entity
	}
	
	public BlogPost(String title, String author, String blogEntry, long id) {
		this.title = title;
		this.author = author;
		this.blogEntry = blogEntry;
		this.id = id;
	}

	public String getTitle() {return title;}
	
	public void setTitle(String title) {this.title = title;}

	public String getAuthor() {return author;}

	public void setAuthor(String author) {this.author = author;}

	public String getBlogEntry() {return blogEntry;}

	public void setBlogEntry(String blogEntry) {this.blogEntry = blogEntry;}
	
	public Long getId() {return id;}
	
		@Override
		public String toString() {
			return "BlogPost [id=" + id + ", title=" + title + ", author=" + author + ", blogEntry=" + blogEntry + "]";
		
		
	}

}
