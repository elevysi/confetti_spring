package com.elevysi.site.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Play;
import com.elevysi.site.entity.Post;
import com.elevysi.site.entity.Publication;
import com.elevysi.site.entity.Publication_;
import com.elevysi.site.pojo.Page;
import com.elevysi.site.pojo.Page.SortDirection;
import com.elevysi.site.service.AlbumService;
import com.elevysi.site.service.CommentService;
import com.elevysi.site.service.PlayService;
import com.elevysi.site.service.PostService;
import com.elevysi.site.service.PublicationService;

@Component
public class JobsBatch {
	
	@Autowired
	private PostService postService;
	@Autowired
	private PlayService playService;
//	@Autowired
//	private AlbumService albumService;
//	
//	@Autowired
//	private PublicationService publicationService;
//	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PublicationService publicationService;

	public void run(){
//		createPublicationsForAllItems();
		handlePublications();
	}
//	
//	public void createPublicationsForAllItems(){
//		
//		List<Post> posts = postService.findAllPostsWithProfile();
//		
//		if(posts != null){
//			for(int i = 0; i < posts.size(); i++){
//				Post ithPost = posts.get(i);
//				/**
//				 * Check if this item has a publication
//				 */
//				Publication publication = publicationService.findPostPublication(ithPost);
//				
//				
//				if(publication == null){
//					/**
//					 * Create a publication for this item
//					 */
//					if(ithPost.getProfile() != null){
//						Publication newPblct = new Publication(ithPost.getProfile(), ithPost.getCreated(), ithPost.getModified(), true);
//						Publication savedPublication = publicationService.saveRemotePublication(newPblct);
//						
//						if(savedPublication != null){
//							/**
//							 * Update the item
//							 */
//							ithPost.setPublication(savedPublication);
//							postService.updatePost(ithPost);
//						}
//					}
//				}
//			}
//		}
//		
//		List<Play> plays = playService.findAllPlaysWithProfile();
//		if(plays != null){
//			for(int i = 0; i < plays.size(); i++){
//				Play ithPlay = plays.get(i);
//				/**
//				 * Check if this item has a publication
//				 */
//				Publication publication = publicationService.findPlayPublication(ithPlay);
//				if(publication == null){
//					/**
//					 * Create a publication for this item
//					 */
//					if(ithPlay.getPlayProfile() != null){
//						Publication newPblct = new Publication(ithPlay.getPlayProfile(), ithPlay.getCreated(), ithPlay.getModified(), true);
//						Publication savedPublication = publicationService.saveRemotePublication(newPblct);
//						
//						if(savedPublication != null){
//							/**
//							 * Update the item
//							 */
//							ithPlay.setPublication(savedPublication);
//							playService.updatePlay(ithPlay);
//						}
//					}
//				}
//			}
//		}
//		
//		
//		List<Album> albums = albumService.findAllProfileAlbums();
//		if(albums != null){
//			for(int i = 0; i < albums.size(); i++){
//				Album ithAlbum = albums.get(i);
//				/**
//				 * Check if this item has a publication
//				 */
//				Publication publication = publicationService.findAlbumPublication(ithAlbum);
//				if(publication == null){
//					
//					/**
//					 * Find the profile since the fetch is lazy
//					 */
////					Album album = albumService.findAlbumWithProfile(ithAlbum);
//					
//					/**
//					 * Create a publication for this item
//					 */
//					if(ithAlbum.getProfileOwner() != null){
//						Publication newPblct = new Publication(ithAlbum.getProfileOwner(), ithAlbum.getCreated(), ithAlbum.getModified(), true);
//						Publication savedPublication = publicationService.saveRemotePublication(newPblct);
//						
//						if(savedPublication != null){
//							/**
//							 * Update the item
//							 */
//							ithAlbum.setPublication(savedPublication);
//							albumService.updateAlbum(ithAlbum);
//						}
//					}
//				}
//			}
//		}
		
//	}
	
	public void handlePublications(){
		Page publicationPage = publicationService.buildOffsetPage(1, 100, Publication_.modified, SortDirection.DESC);
		List<Publication> publications = publicationService.getAllPublications(publicationPage);
		if(publications != null){
			for (Publication publication : publications) {
				/**
				 * Create slug if not existing
				 * Update number of comments
				 */
				Post post = publication.getPost();
				if(post != null){
					int postCommentsCount = commentService.itemCommentsCount(post.getId(), "posts");
					post.setCommentCount(postCommentsCount);
					postService.doSaveEditedPost(post);
					
					if(publication.getFriendlyUrl() == null){
						String postTitle = post.getTitle();
						if(postTitle != null){
							String slug = publicationService.toSlug(postTitle);
							publication.setFriendlyUrl(slug);
							publicationService.doSaveEditedPublication(publication);
						}
					}
				}
				
				Play play = publication.getPlay();
				if(play != null){
					int playCommentsCount = commentService.itemCommentsCount(play.getId(), "plays");
					play.setCommentCount(playCommentsCount);
					playService.doSaveEditedPlay(play);
					
					if(publication.getFriendlyUrl() == null){
						String playTitle = play.getTitle();
						if(playTitle != null){
							String slug = publicationService.toSlug(playTitle);
							publication.setFriendlyUrl(slug);
							publicationService.doSaveEditedPublication(publication);
						}
					}
				}
			}
		}
	}
}
