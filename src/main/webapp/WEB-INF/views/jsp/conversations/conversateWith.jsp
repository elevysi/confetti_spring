<%@ include file="../../layout/taglib.jsp"%>

<h2><c:out value="${actingProfile.name}"/></h2>
<a class="btn-u rounded btn-u-dark-blue btn-u-xs"  href="<spring:url value='/conversations/conversate' />"><i class="fa fa-magic"></i> New Conversation</a>
<c:url var="getPrevMsgsUrl" value="/conversations/${reciever.name}/${conversation.uuid}" />
<c:url var="postMsgUrl" value="/conversations/${reciever.name}/${conversation.uuid}" />
<c:set var="proAuthor" value="${actingProfile.name}" />
<div id="messagesReactContainer"></div>
<script type="text/babel">

var PreviousMessages = React.createClass({
	render: function(){
		var messageNodes = this.props.chatMsgsData.map(function(message){
			return (
				<Message author={message.messageAuthor} key={message.id}>
					{message.messageContent}
				</Message>
			);
		});
		return(
			<div>
				Messages
				{messageNodes} 
			</div>
		);
	}
});

var MessageForm = React.createClass({
	getInitialState: function(){
		return {author: '${proAuthor}', msg: ''};
	},
	handleAuthorChange: function(e){
		this.setState({author: ${proAuthor}});
	},
	handleMsgChange: function(e){
		this.setState({msg: e.target.value});
	},
	handleSubmit: function(e){
		e.preventDefault();
		var author = this.state.author.trim();
		var msg = this.state.msg.trim();

		if(!msg){
			return;
		}

		this.props.onMessageSubmit({author: author, msg: msg});
		this.setState({author: "${proAuthor}",  msg: ""});
	},	
	render : function(){
		return(
				<form className="commentForm" onSubmit={this.handleSubmit}>
					<textarea value={this.state.msg} placeholder="Message" onChange={this.handleMsgChange}/>
					<input type="hidden" value={this.state.author} onChange={this.handleAuthorChange} />
       				<input type="submit" value="Send" />
      			</form>
		);
	}
});

var Message = React.createClass({
	render : function(){
		return (
			<div>
				<h4>{this.props.author}</h4>
				{this.props.children}
			</div>
		);
	}
});

var ChatBox = React.createClass({
	loadMessages : function(){
		$.ajax({
			url: this.props.getChatMsgsUrl,
			success: function(json){
				this.setState({chatMsgs: json});
			}.bind(this),
			dataType: 'json',
			cache: false
		});
	},
	handleMsgSubmit: function(Message){
		$.ajax({
			url: this.props.getChatMsgsUrl,
			type: 'POST',
			dataType: 'json',
			data: Message,
			beforeSend:function(xhr){
  	 	         xhr.setRequestHeader(header, token)
			},
			success: function(json){
				this.setState({chatMsgs: json});
			}.bind(this),
			error: function(xhr, status, err){
				console.error(this.props.getChatMsgsUrl, status, err.toString());
			}.bind(this),
			
		});

	},
	getInitialState: function(){
		return {chatMsgs: []};
	},
	componentDidMount: function(){
		this.loadMessages();
		setInterval(this.loadMessages, this.props.pollInterval);
	},
	render : function(){
		return (
			<div>
				<PreviousMessages chatMsgsData={this.state.chatMsgs}/>
				<MessageForm onMessageSubmit={this.handleMsgSubmit}/>
			</div>
		);
	}
});

ReactDOM.render(
	<ChatBox getChatMsgsUrl="${getPrevMsgsUrl}" pollInterval={2000}/>,
	document.getElementById("messagesReactContainer")
);

</script>