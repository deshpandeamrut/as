package com.dashlines.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dashlines.entity.Article;
import com.dashlines.entity.Source;
import com.dashlines.service.NewsServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "NEWS API")
@RestController
public class NewsService {

	@Autowired
	NewsServiceImpl newsServiceImpl;

	@ApiOperation(value = "Retrieves latest news articles from poular sources")
	@RequestMapping(value = "/news/getLatest")
	@ResponseBody
	public List<Article> getLatestNews() {
		return newsServiceImpl.getLatestNews();
	}

	@ApiOperation(value = "Retrieves latest news articles from the specified source")
	@RequestMapping("/news/source/{sourceName}")
	@ResponseBody
	public List<Article> getNewsFromSource(@PathVariable String sourceName) {
		return newsServiceImpl.getNewsFromSource(sourceName);
	}

	@ApiOperation(value = "Retrieves latest news articles for the specifies keyword")
	@RequestMapping(value = "/news/search/{searchText}")
	@ResponseBody
	public List<Article> getLatestNewsForSearchText(@PathVariable String searchText) {
		List<Article> temp = newsServiceImpl.getLatestNewsForSearchText(searchText);
		if (temp.isEmpty()) {
			temp = newsServiceImpl.getAllNewsForSearchText(searchText);
		}
		return temp;
	}

	@ApiOperation(value = "Retrieves news articles from the specified source")
	@RequestMapping(value = "/news/all/search/{searchText}")
	@ResponseBody
	public List<Article> getAllNewsForSearchText(@PathVariable String searchText) {
		return newsServiceImpl.getAllNewsForSearchText(searchText);
	}

	@ApiOperation(value = "Retrieves latest news articles from the specified category")
	@RequestMapping(value = "/news/category/{category}")
	@ResponseBody
	public List<Article> getNewsForCategory(@PathVariable String category, boolean extendedSearch) {
		return newsServiceImpl.getNewsForCategory(category, extendedSearch);
	}

	@ApiOperation(value = "Retrieves personilized news articles")
	@RequestMapping(value = "/news/myFeed")
	@ResponseBody
	public Map<String, List<Article>> getLatestFeed() {
		return newsServiceImpl.getLatestFeed();
	}

	@ApiOperation(value = "Retrieves available news sources")
	@RequestMapping(value = "/news/sources")
	@ResponseBody
	public List<Source> getNewsSources() {
		return newsServiceImpl.getNewsSources();
	}

	@ApiOperation(value = "Retrieves smart feed")
	@RequestMapping(value = "/news/smartFeed")
	@ResponseBody
	public List<Article> getSmartNews() {
		return newsServiceImpl.getSmartNews("");
	}

	@ApiOperation(value = "Retrieves latest news articles from the user interested sources")
	@RequestMapping(value = "/news/mySources")
	@ResponseBody
	public Map<String, List<Article>> getMySourcesNews() {
		return newsServiceImpl.getMySourcesNews();
	}
}