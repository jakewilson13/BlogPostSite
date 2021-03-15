package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //controls what the server gives to the client
public class BlogPostController {
	
	@Autowired
	private FakeBlogs fakeBlogs;

	
	private static List<BlogPost> posts = FakeBlogs.makeFakeBlogs();
	//posts is empty
	// private BlogPost blogPost;
	
	@GetMapping(value="/")
	public String index(BlogPost blogPost, Model model) {
		posts = FakeBlogs.allBlogs();
		model.addAttribute("posts", posts);
		return "blogpost/index";
	}
	@GetMapping(value="blogpost/new")
	public String newBlog(BlogPost blogPost) {
		return "blogpost/new";
	}
	
	@PostMapping(value="blogpost/new")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		BlogPost newPost = FakeBlogs.createBlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry());	
			FakeBlogs.addNewPost(newPost);
		    posts = FakeBlogs.allBlogs();
		    
		    model.addAttribute("title", blogPost.getTitle());
			model.addAttribute("author", blogPost.getAuthor());
			model.addAttribute("blogEntry",blogPost.getBlogEntry());
			model.addAttribute("id" , blogPost.getId());
			
			fakeBlogs.save(blogPost);
			
			return "blogpost/result";
	}
	//Search by author
	@PostMapping(value="blogpost/author")		
	public String searchByAuthor(BlogPost blogPost, String author, Model model) {
		System.out.println(author);
		posts = FakeBlogs.searchByAuthor(author);
		model.addAttribute("posts", posts);
		return "blogpost/index";		
	    }
	
	@PostMapping(value = "/blogpost/edit")
	public String updatedBlogPost(Long id, BlogPost blogPost, Model model) {
		System.out.println(id);
		FakeBlogs.updateBlogPost(id, blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry());
	
		model.addAttribute("title", blogPost.getTitle());
		model.addAttribute("author", blogPost.getAuthor());
		model.addAttribute("blogEntry", blogPost.getBlogEntry());
		model.addAttribute("id", blogPost.getId());
		
		return "blogpost/result";
	}

	
	@PostMapping(value = "blogpost/edit/{id}")
	public String editBlogPost(@PathVariable Long id, BlogPost blogPost, Model model) {
	    BlogPost postToUpdate = FakeBlogs.getBlogById(id);
		model.addAttribute(postToUpdate);
	    System.out.println("postToUpdate");
	    return "blogpost/edit";
	
	
	}
	
	
	
	@RequestMapping(value="blogpost/{id}", method = RequestMethod.POST)
	public String deletePostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
		System.out.println(id);
		
		FakeBlogs.deletePost(id);
		posts=FakeBlogs.allBlogs();
		
		model.addAttribute("posts", posts);

		return"blogpost/index";

		}
}
	
	

