/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月30日;
//-------------------------------------------------------
public class DesignTwitter {

  /*
  Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
  
    postTweet(userId, tweetId): Compose a new tweet.
    getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
    follow(followerId, followeeId): Follower follows a followee.
    unfollow(followerId, followeeId): Follower unfollows a followee.
  
  Example:
  
  Twitter twitter = new Twitter();
  
  // User 1 posts a new tweet (id = 5).
  twitter.postTweet(1, 5);
  
  // User 1's news feed should return a list with 1 tweet id -> [5].
  twitter.getNewsFeed(1);
  
  // User 1 follows user 2.
  twitter.follow(1, 2);
  
  // User 2 posts a new tweet (id = 6).
  twitter.postTweet(2, 6);
  
  // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
  // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
  twitter.getNewsFeed(1);
  
  // User 1 unfollows user 2.
  twitter.unfollow(1, 2);
  
  // User 1's news feed should return a list with 1 tweet id -> [5],
  // since user 1 is no longer following user 2.
  twitter.getNewsFeed(1);  
  */

  public class Twitter {

    int now = 0;

    Map<Integer, User> userMap = new HashMap<>();

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
      User user = getUser(userId);
      user.newest = new Tweet(tweetId, user.newest);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item
     * in the news feed must be posted by users who the user followed or by the
     * user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
      List<Integer> result = new ArrayList<>();
      TreeSet<Tweet> heap = new TreeSet<>((t1, t2) -> t2.time - t1.time);
      User user = getUser(userId);
      if (Objects.nonNull(user.newest)) heap.add(user.newest);
      user.followees.stream().map(id -> getUser(id).newest).filter(Objects::nonNull).forEach(heap::add);
      while (result.size() < 10 && !heap.isEmpty()) {
        Tweet t = heap.pollFirst();
        result.add(t.id);
        if (Objects.nonNull(t.prev)) heap.add(t.prev);
      }
      return result;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a
     * no-op.
     */
    public void follow(int followerId, int followeeId) {
      if (followeeId != followerId) getUser(followerId).followees.add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be
     * a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
      getUser(followerId).followees.remove(followeeId);
    }

    private User getUser(int id) {
      return userMap.computeIfAbsent(id, k -> new User());
    }

    class User {

      Tweet newest = null;

      Set<Integer> followees = new HashSet<>();
    }

    class Tweet {

      int id, time = now++;

      Tweet prev;

      public Tweet(int id, Tweet prev) {
        this.id = id;
        this.prev = prev;
      }
    }
  }

  @Test
  public void test() {
    Twitter twitter = new Twitter();

    // User 1 posts a new tweet (id = 5).
    twitter.postTweet(1, 5);

    // User 1's news feed should return a list with 1 tweet id -> [5].
    assertEquals(Arrays.asList(5), twitter.getNewsFeed(1));

    // User 1 follows user 2.
    twitter.follow(1, 2);

    // User 2 posts a new tweet (id = 6).
    twitter.postTweet(2, 6);

    // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
    // Tweet id 6 should precede tweet id 5 because it is posted after tweet id
    // 5.
    assertEquals(Arrays.asList(6, 5), twitter.getNewsFeed(1));

    // User 1 unfollows user 2.
    twitter.unfollow(1, 2);

    // User 1's news feed should return a list with 1 tweet id -> [5],
    // since user 1 is no longer following user 2.
    assertEquals(Arrays.asList(5), twitter.getNewsFeed(1));
  }
}
