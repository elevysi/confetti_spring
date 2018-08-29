package com.elevysi.site.blog.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elevysi.site.blog.entity.PublicationType;

@Repository
@Transactional
public class PublicationTypeDAOImplement extends AbstractDAOImpl<PublicationType, Integer> implements PublicationTypeDAO{

}
