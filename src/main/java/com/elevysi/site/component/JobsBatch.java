package com.elevysi.site.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elevysi.site.entity.Album;
import com.elevysi.site.entity.Play;
import com.elevysi.site.entity.Post;
import com.elevysi.site.entity.Publication;
import com.elevysi.site.service.AlbumService;
import com.elevysi.site.service.CommentService;
import com.elevysi.site.service.PlayService;
import com.elevysi.site.service.PostService;
import com.elevysi.site.service.PublicationService;

@Component
public class JobsBatch {
	
//	@Autowired
//	private PostService postService;
//	@Autowired
//	private PlayService playService;
//	@Autowired
//	private AlbumService albumService;
//	
//	@Autowired
//	private PublicationService publicationService;
//	
//	@Autowired
//	private CommentService commentService;
//	
////	private Publi
//	
	public void run(){
//		createPublicationsForAllItems();
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
	
	public void updateCommentsCountForItems(){
		
	}

}
