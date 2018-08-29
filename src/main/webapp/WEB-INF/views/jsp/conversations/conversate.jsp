<%@ include file="../../layout/taglib.jsp"%>

<h2><c:out value="${actingProfile.name}"/></h2>
<a class="btn-u rounded btn-u-dark-blue btn-u-xs"  href="<spring:url value='/conversations/conversate' />"><i class="fa fa-magic"></i> New Conversation</a>
<c:url var="getFriendsUrl" value="/correspondents/friends" />
<div id="messagesReactContainer"></div>
<script type="text/babel">
// tutorial8.js
var friendsList = [
  {id: 1, name: "Pete Hunt", text: "This is one comment"},
  {id: 2, name: "Jordan Walke", text: "This is *another* comment"}
];

var CorrespondentForm = React.createClass({
  render: function() {
    return (
      <div className="correspondentForm">
        Hello, world! I am a CorrespondentForm.
      </div>
    );
  }
});

var CorrespondentList = React.createClass({
	render:function(){
		var correspondentNodes = this.props.data.map(function(profile){
			return(
				<Correspondent profileName={profile.name} key={profile.id}> {profile.name} </Correspondent>
			)
		});
		return (

			<div>{correspondentNodes}</div>

			
		);
	}
});


var FriendsOptions = React.createClass({
	
	render:function(){
		var friendsOptions = this.props.data.map(function(friend){
		  return(
			 <option key={friend.id} value={friend.name}>{friend.name}</option>
		  )
	   });

		return (
			<select>{friendsOptions}</select>
		);
	}
});

var CorrespondentBox = React.createClass({
  loadCorrespondentFromServer: function(){
 	$.ajax({
      url: this.props.url,
      dataType: 'json',
      cache: false,
      success: function(data) {
        this.setState({data: data});
      }.bind(this),
      error: function(xhr, status, err) {
        console.error(this.props.url, status, err.toString());
      }.bind(this)
    });
  },
  getInitialState: function() {
    return {data: []};
  },
  componentDidMount: function(){
	this.loadCorrespondentFromServer();
  },
  render: function() {
    return (
<div>
		<form><CorrespondentList data={this.state.data} /></form>
       	<CorrespondentForm />
		<FriendsOptions data={this.state.data} />
</div>
    );
  }
});

var Correspondent = React.createClass({
	render : function(){
		return (
			<div className="Correspondent">
				{this.props.name}
				{this.props.children}
			</div>
		);
	}
});

ReactDOM.render(
  <CorrespondentBox url="${getFriendsUrl}" />,
  document.getElementById('messagesReactContainer')
);

</script>