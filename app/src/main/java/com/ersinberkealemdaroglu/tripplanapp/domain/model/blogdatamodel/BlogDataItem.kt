package com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel


data class PostsPublish(
    val ID: Int,
    val ancestors: List<Any>,
    val cc_categories: List<CcCategory>,
    val cc_tags: List<CcTag>,
    val comment_count: String,
    val comment_status: String,
    val filter: String,
    val guid: String,
    val menu_order: Int,
    val page_template: String,
    val ping_status: String,
    val pinged: String,
    val post_author: String,
    val post_category: List<Int>,
    val post_content: String,
    val post_content_filtered: String,
    val post_date: String,
    val post_date_gmt: String,
    val post_excerpt: String,
    val post_mime_type: String,
    val post_modified: String,
    val post_modified_gmt: String,
    val post_name: String,
    val post_parent: Int,
    val post_password: String,
    val post_status: String,
    val post_title: String,
    val post_type: String,
    val tags_input: List<String>,
    val to_ping: String,
)