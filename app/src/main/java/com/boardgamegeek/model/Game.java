package com.boardgamegeek.model;

import com.boardgamegeek.util.StringUtils;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.text.DecimalFormat;
import java.util.List;

@Root(name = "item")
public class Game {
    @Root(name = "name")
    public static class Name {
        @Attribute public String type;
        @Attribute public int sortindex;
        @Attribute public String value;
    }

    @Root(name = "poll")
    public static class Poll {
        @Attribute public String name;
        @Attribute public String title;
        @Attribute public int totalvotes;
        @ElementList(required = false, inline = true, empty = false) public List<Results> results;
    }

    @Root(name = "results")
    public static class Results {
        @Attribute(required = false) public String numplayers;
        @ElementList(required = false, inline = true, empty = false) public List<Result> result;
    }

    @Root(name = "result")
    public static class Result {
        @Attribute(required = false) public int level;
        @Attribute public String value;
        @Attribute public int numvotes;
    }

    public static class Link {
        @Attribute public String type;
        @Attribute public int id;
        @Attribute public String value;
        @Attribute(required = false) public String inbound;
    }

    @Root(name = "rank")
    public static class Rank {
        @Attribute public String type;
        @Attribute public int id;
        @Attribute public String name;
        @Attribute(name = "friendlyname") public String friendlyName;
        @Attribute public String value;
        @Attribute public String bayesaverage;
    }

    public static class Statistics {
        @Attribute private int page;
        @Path("ratings/usersrated") @Attribute(name = "value") public String usersRated;
        @Path("ratings/average") @Attribute(name = "value") public String average;
        @Path("ratings/bayesaverage") @Attribute(name = "value") public String bayesAverage;

        @Path("ratings") @ElementList public List<Rank> ranks;
        @Path("ratings/stddev") @Attribute(name = "value") public String standardDeviation;
        @Path("ratings/median") @Attribute(name = "value") public String median;
        @Path("ratings/owned") @Attribute(name = "value") public String owned;
        @Path("ratings/trading") @Attribute(name = "value") public String trading;
        @Path("ratings/wanting") @Attribute(name = "value") public String wanting;
        @Path("ratings/wishing") @Attribute(name = "value") public String wishing;
        @Path("ratings/numcomments") @Attribute(name = "value") public String commenting;
        @Path("ratings/numweights") @Attribute(name = "value") public String weighting;
        @Path("ratings/averageweight") @Attribute(name = "value") public String averageWeight;
    }

    @Root(name = "comment")
    public static class Comment {
        private static final DecimalFormat RATING_FORMAT = new DecimalFormat("#0.00");

        @Attribute
        public String username;

        @Attribute
        private String rating;

        public double getRating() {
            return StringUtils.parseDouble(rating, 0.0);
        }

        public String getRatingText() {
            double rating = getRating();
            if (rating < 1.0) {
                return "N/A";
            }
            return RATING_FORMAT.format(rating);
        }

        @Attribute
        public String value;
    }

    @Attribute public String type;
    @Attribute public int id;
    @Element(required = false) public String thumbnail;
    @Element(required = false) public String image;
    @ElementList(inline = true, required = false) public List<Name> names;
    @Element(required = false) public String description;
    @Path("yearpublished") @Attribute(name = "value") public String yearpublished;
    @Path("minplayers") @Attribute(name = "value") public String minplayers;
    @Path("maxplayers") @Attribute(name = "value") public String maxplayers;
    @Path("playingtime") @Attribute(name = "value") public String playingtime;
    @Path("minplaytime") @Attribute(name = "value") public String minplaytime;
    @Path("maxplaytime") @Attribute(name = "value") public String maxplaytime;
    @Path("minage") @Attribute(name = "value") public String minage;

    @ElementList(inline = true, required = false)
    public List<Poll> polls;

    @ElementList(inline = true, required = false)
    public List<Link> links;

    @Element(name = "statistics", required = false)
    public Statistics statistics;

    @Element(required = false)
    public Comments comments;

    public static class Comments {
        @Attribute public int page;
        @Attribute public int totalitems;
        @ElementList(inline = true) public List<Comment> comments;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}