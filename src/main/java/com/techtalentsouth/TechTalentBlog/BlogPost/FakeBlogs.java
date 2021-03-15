package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FakeBlogs {

	
	@Autowired
	BlogPostRepository blogRepository;
	
	private static List<BlogPost> blogPosts;

	public static List<BlogPost> makeFakeBlogs() {
		blogPosts = new ArrayList<>();

		blogPosts.add(new BlogPost("First Blog", "Leslie",
				"Eggnog tree cookie wreath give give, jingle bells wreath icicle Christmas nutcracker bells. Frosty santa goodwill reindeer love fireplace stocking goodwill, give noel guest reindeer. Noel holiday chestnuts carols, cookie goodwill frosty love eggnog goodwill guest. Elves chestnuts jingle bells goodwill angel stocking icicle partridge, love gold scarf holiday decorate.",
				1));

		blogPosts.add(new BlogPost("Second", "Not Leslie",
				"Bells frosty chestnuts scarf lights chimney, gift december ivy celebrate cookie. Angel give elves scarf partridge celebrate santa. Elves wreath partridge gift candy cane, frosty jingle bells holiday reindeer love lights noel santa elves. Yule fireplace chestnuts ornament holiday scarf, eggnog gift love gold calendar. ",
				2));

		blogPosts.add(new BlogPost("Third", "Still Not Leslie",
				"Frosty goodwill tree december fireplace, cookie give noel partridge ivy fireplace chimney tree. Love noel cookie cookie singing fireplace scarf. Wreath toys toys santa tree reindeer elves eggnog gold, Christmas love eggnog chimney jingle bells. ",
				3));

		blogPosts.add(new BlogPost("Fourth", "Might be Leslie",
				"December tree candy cane ornament jingle bells eggnog frosty icicle holiday. Guest candy cane yule celebrate tree wreath holiday give holiday, calendar cookie wreath santa.",
				4));

		return blogPosts;
	}
	
	public void save(BlogPost blogPost) {
		blogRepository.save(blogPost);
	}

	public static List<BlogPost> allBlogs() {
		return blogPosts;
	}

	public static BlogPost getBlogById(long id) {
		for (BlogPost post : blogPosts) {
			if (post.getId() == id) {
			return post;
			}
		}
		System.out.println("Id not found");
		return null;
	}

	// create new blog post and add it to list of posts
	// figure out how to create unique id for our blogposts
	public static BlogPost createBlogPost(String title, String author, String blogEntry) {
		// create a unique id
		long id = createId();
		// create new post to the list
		BlogPost newPost = new BlogPost(title, author, blogEntry, id);
		return newPost;
	}

	public static void addNewPost(BlogPost newPost) {
		blogPosts.add(newPost);
	}

	public static long createId() {
		System.out.println("createId started");
		int listSize = blogPosts.size();
		System.out.println(listSize);
		BlogPost lastPost = blogPosts.get(listSize - 1);
		long id = lastPost.getId() + 1;
		System.out.println(lastPost.getId());
		System.out.println(id);
		return id;
	}

	public static BlogPost updateBlogPost(long id, String title, String author, String blogEntry) {
		for (BlogPost post : blogPosts) {
			if (post.getId() == id) {
				int postIndex = blogPosts.indexOf(post);
				post.setTitle(title);
				post.setBlogEntry(blogEntry);
				post.setAuthor(author);
				blogPosts.set(postIndex, post);
				return post;
			}
		}
		System.out.println("No such post exists to update");
		return null;
	}

	// method to delete posts from list (returns true when complete)
	public static boolean deletePost(long id) {
		int postIndex = -1;
		for (BlogPost post : blogPosts) {
			if (post.getId() == id) {
				postIndex = blogPosts.indexOf(post);
				continue;
			}
		}
		if (postIndex > -1) {
			blogPosts.remove(postIndex);
		}
		return true;
	}

	// searching the list of blogposts by the author
	public static List<BlogPost> searchByAuthor(String author) {
		System.out.printf("search for %s\n", author);
		List<BlogPost> newPosts = new ArrayList<>();
		// getting the author by search while ignoring it being case sensitive
		for (BlogPost post : blogPosts) {
			if (post.getAuthor().equalsIgnoreCase(author)) {
				newPosts.add(post);
			}
		}
		// if the posts have the correct author it returns the post
		if (newPosts.size() > 0) {
			return newPosts;
		}
		// else the author will not be found
		else {
			System.out.println("Author not found");
			return null;
		}
	}
}
