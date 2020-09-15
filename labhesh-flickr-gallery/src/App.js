import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

const INITIAL_STATE = {
    pictures: [],
      favList: [],
      indexvalue: 0,
      photosTitle:{},
      favsTitle:{},
      textInput: 'dog',
      isFavView :false,
      isNormalView : true
};

class App extends Component {
  constructor(){
    super();
    this.state = INITIAL_STATE;
  }

  componentDidMount(){
    this.ReloadImages();
  }

  ReloadImages = () => {
    fetch('https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key='+KEY+'&tags='+this.state.textInput+'&per_page=50&page=1&format=json&nojsoncallback=1')
    .then(function(response){
      return response.json();
    })
    .then(function(j){
      let picArray = j.photos.photo.map((pic) => {
        this.setState({
          photosTitle : j.photos.photo.map(pic=>pic.title)
        })
        
        var srcPath = 'https://farm'+pic.farm+'.staticflickr.com/'+pic.server+'/'+pic.id+'_'+pic.secret+'.jpg';
        return(
          <img alt="dogs" src={srcPath}></img>
        )
      })
      this.setState({pictures: picArray});
    }.bind(this))
  }

  NextHandler = () => {
    var currentIndex = this.state.indexvalue;
    if(currentIndex === 9)
    {
      currentIndex = 0;
    }
    else{
      currentIndex++;
    }
    this.setState({indexvalue: currentIndex});
  }

  PrevHandler = () => {
    var currentIndex = this.state.indexvalue;
    if(currentIndex === 0)
    {
      currentIndex = 9;
    }
    else{
      currentIndex--;
    }
    this.setState({indexvalue: currentIndex});
  }

  favHandler = () => {
      this.state.favList.push(this.state.pictures[this.state.indexvalue])
  }

  delFavHandler = () => {
    this.state.favList.pop(this.state.pictures[this.state.indexvalue])
    this.showFav();
}

  showFav = () => {
    this.setState({isFavView : true, isNormalView: false})
  }

  HandleChange = (e) => {
    this.setState({textInput: e.target.value});
  }

  Delay = (function(){
    var timer = 0;
    return function(callback, ms){
      clearTimeout(timer);
      timer = setTimeout(callback, ms);
    }
  })();

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
          <button onClick={this.showFav}>Go To Favorites</button>
        </header>
        {this.state.isNormalView && <p className="App-intro">
          <div>
          Title: {this.state.photosTitle[this.state.indexvalue]}
          </div>
          {this.state.pictures[this.state.indexvalue]}
        </p>
        }

        {this.state.isFavView && <p className="App-intro">
          <div>
            {this.state.favList.map(fav => {
            return (
            <button onClick={this.delFavHandler}>{fav} <br></br>Delete Favorite </button>);
            })}
          
          </div>
        </p>
        
        }
        <p>
          <input className="textInput"
          onChange={this.HandleChange}
          onKeyUp={() => this.Delay(function(){
            this.ReloadImages();
          }.bind(this), 1000)}
          ></input> 
        </p>
        <div>
          <button onClick={this.PrevHandler}>Prev</button>&nbsp;
          <button onClick={this.NextHandler}>Next</button>&nbsp;
          <button onClick={this.favHandler}>Mark Favorite</button>
        </div>
      </div> 
    );
  }
}

export default App;