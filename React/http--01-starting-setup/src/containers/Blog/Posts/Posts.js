import React, { Component } from "react";
import { Link, Route} from 'react-router-dom';

import Post from '../../../components/Post/Post';
import FullPost from '../FullPost/FullPost';
import './Posts.module.css';

import axios from 'axios';

class Posts extends Component {
  state = {
    posts: []
  }

  postSelectedHandler = (id) => {
    this.props.history.push({ pathname: '/posts/' + id });
  }

  componentDidMount() {
    console.log(this.props);
    axios.get('/posts')
      .then(response => {
        const posts = response.data.slice(0, 4);
        const updatedPosts = posts.map(post => {
          return {
            ...post,
            author: 'Nilesh'
          }
        });
        this.setState({ posts: updatedPosts });
      })
      .catch(error => {
        console.log(error);
      });
  }

  render() {
    let posts = <p style={{ textAlign: "center" }}>Something went wrong!</p>
    if (!this.state.error) {
      posts = this.state.posts.map(post =>
        <Link to={'/posts/' + post.id} key={post.id}>
          <Post
            title={post.title}
            author={post.author}
            clicked={() => this.postSelectedHandler(post.id)} />
        </Link>
      );
    }

    return (
      <div>
        <section className="Posts">
          {posts}
        </section>
        <Route path="/posts/:id" exact component={FullPost} />
      </div>
    );
  }
}

export default Posts;