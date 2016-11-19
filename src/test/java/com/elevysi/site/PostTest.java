package com.elevysi.site;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elevysi.site.entity.Post;
import com.elevysi.site.service.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:dispatcher-servlet.xml", "classpath*:application-context.xml", "classpath*:datasource.xml"})
public class PostTest {
	
	
	@Autowired
	PostService postService;
	
	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	@Test
	public void createPost(){
		Post post = new Post();
		post.setTitle("New Post");
		post.setDescription("First Added Test Post");
		postService.savePost(post);
		
		int id = post.getId();
	    post = null;
	    Post foundPost = postService.findOne(id);
	    Assert.assertEquals("New Post", foundPost.getTitle());
	    foundPost = null;
	}

}
