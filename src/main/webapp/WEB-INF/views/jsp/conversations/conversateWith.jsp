<%@ include file="../../layout/taglib.jsp"%>

<c:url var="getPrevMsgsUrl" value="/conversations/${reciever.name}/${conversation.uuid}" />
<c:url var="downloadAvatarUrl" value="/uploads/download?key=" />
<c:url var="profileViewUrl" value="/profile?username=" />
<c:url var="postMsgUrl" value="/conversations/${reciever.name}/${conversation.uuid}" />
<c:set var="proAuthor" value="${actingProfile.name}" />

<div class="row">
	<div class="panel panel-profile">
		<div class="panel-heading overflow-h">
			<h2 class="panel-title heading-sm pull-left"><i class="fa fa-comments-o"></i> Messages</h2>
			<a href="" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-xs pull-right">New Message</a>
		</div>
		<div id="scrollbar4" class="panel-body no-padding mCustomScrollbar _mCS_5 mCS-autoHide" data-mcs-theme="minimal-dark" style="position: relative; overflow: visible;">
			<div id="mCSB_5" class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside" tabindex="0">
				<div id="mCSB_5_container" class="mCSB_container" style="position: relative; top: 0px; left: 0px;" dir="ltr">
					
				</div>
			</div>
			<div id="mCSB_5_scrollbar_vertical" class="mCSB_scrollTools mCSB_5_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical" style="display: block;">
				<div class="mCSB_draggerContainer">
					<div id="mCSB_5_dragger_vertical" class="mCSB_dragger" style="position: absolute; min-height: 50px; display: block; height: 162px; max-height: 286px; top: 0px;" oncontextmenu="return false;">
						<div class="mCSB_dragger_bar" style="line-height: 50px;"></div>
					</div>
					<div class="mCSB_draggerRail"></div>
				</div>
			</div>
		</div>
	</div>
</div>	

<script type="text/babel">

var PreviousMessages = React.createClass({
	render: function(){
		var messageNodes = this.props.chatMsgsData.map(function(message){
			return (
				<Message author={message.messageAuthor} key={message.id} avatar={message.avatarUUID}>
					{message.messageContent}
				</Message>
			);
		});
		return(
				<div>
					{messageNodes}	
				</div>
		);
	}
});

var MessageForm = React.createClass({
	getInitialState: function(){
		return {author: '${proAuthor}', msg: '', avatar: ''};
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
				
				<form className="sky-form" onSubmit={this.handleSubmit}>
<fieldset>
					
						<textarea value={this.state.msg} placeholder="Message" onChange={this.handleMsgChange} className="form-control"/>
						<input type="hidden" value={this.state.author} onChange={this.handleAuthorChange} />
					
</fieldset>
<footer>
				<input type="submit" className="btn-u" value="Send" />
</footer>
				</form>
		);
	}
});

var Message = React.createClass({
	render : function(){
		return (
				<div className="comment">
					<img src={'${downloadAvatarUrl}'+this.props.avatar} alt="" className="mCS_img_loaded rounded-x" />
					<div className="overflow-h">
						<a href={'${profileViewUrl}'+this.props.author}>{this.props.author}</a>
						<small className="pull-right">
							
							<ul className="list-inline comment-list">
								<li>25m</li>
								<li><i className="fa fa-trash"></i> <a href="">Delete</a></li>
							</ul>
						
						</small>
						<p>
							{this.props.children}
						</p>
					</div>
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
	document.getElementById("mCSB_5_container")
);

</script>