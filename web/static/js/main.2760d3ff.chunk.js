(this.webpackJsonpticketfrontend=this.webpackJsonpticketfrontend||[]).push([[0],{159:function(e,t,n){},160:function(e,t,n){},466:function(e,t,n){"use strict";n.r(t);var o=n(0),s=n.n(o),i=n(9),a=n.n(i),c=(n(159),n(21)),l=n(22),r=n(29),h=n(28),d=(n.p,n(160),n(16)),u=(n(161),n(51)),j=n(34),b=n(39),v=n(104),m=n(64),f=n(49),k=n(1),O=function(e){Object(r.a)(n,e);var t=Object(h.a)(n);function n(){var e;return Object(c.a)(this,n),(e=t.call(this)).state={name:"React",showBookingDetail:!0},e.handleShow=e.handleShow.bind(Object(d.a)(e)),e.handleClose=e.handleClose.bind(Object(d.a)(e)),e}return Object(l.a)(n,[{key:"handleShow",value:function(e){}},{key:"handleClose",value:function(e){this.props.handleClose(e)}},{key:"handleSave",value:function(e){this.props.handlePurchase(e)}},{key:"render",value:function(){var e=this,t=this.props.movie,n=this.props.movieTime,o=this.props.selectedSeat;return Object(k.jsx)(k.Fragment,{children:Object(k.jsxs)(u.a,{show:!0,onHide:function(){return e.handleClose()},backdrop:"static",keyboard:!1,children:[Object(k.jsx)(u.a.Header,{closeButton:!0,children:Object(k.jsxs)(u.a.Title,{children:[t.name," - ",n.From]})}),Object(k.jsxs)(u.a.Body,{children:[Object(k.jsx)("h5",{children:"You have selected Seat "}),o.map((function(e){return Object(k.jsx)(f.a,{id:"tbg-check-{s}",value:e,variant:"primary",disable:!0,children:e})})),Object(k.jsxs)(b.a,{children:[Object(k.jsxs)(b.a.Group,{as:v.a,className:"mb-3",controlId:"formPlaintextName",children:[Object(k.jsx)(b.a.Label,{column:!0,sm:"2",children:"Name"}),Object(k.jsx)(m.a,{sm:"10",children:Object(k.jsx)(b.a.Control,{plaintext:!0})})]}),Object(k.jsxs)(b.a.Group,{as:v.a,className:"mb-3",controlId:"formPlaintextEmail",children:[Object(k.jsx)(b.a.Label,{column:!0,sm:"2",children:"Email"}),Object(k.jsx)(m.a,{sm:"10",children:Object(k.jsx)(b.a.Control,{plaintext:!0})})]})]})]}),Object(k.jsxs)(u.a.Footer,{children:[Object(k.jsx)(j.a,{variant:"secondary",onClick:function(){return e.handleClose()},children:"Close"}),Object(k.jsx)(j.a,{variant:"primary",onClick:function(){return e.handleSave()},children:"Checkout"})]})]})})}}]),n}(o.Component),p=n(155),S=n(152),g=(n(465),function(e){Object(r.a)(n,e);var t=Object(h.a)(n);function n(){var e;return Object(c.a)(this,n),(e=t.call(this)).state={name:"React",showBookingDetail:!1,selectedMovie:null,selectedSeat:[]},e.confirmBooking=e.confirmBooking.bind(Object(d.a)(e)),e.handleChange=e.handleChange.bind(Object(d.a)(e)),e}return Object(l.a)(n,[{key:"handleChange",value:function(e,t){console.log("val : "),console.log(t),this.state.selectedSeat.some((function(e){return t===e}))?(console.log("value exist, remove"),this.setState({selectedSeat:this.state.selectedSeat.filter((function(e){return e!==t}))})):this.setState({selectedSeat:this.state.selectedSeat.concat(t)}),console.log(this.state.selectedSeat)}},{key:"confirmBooking",value:function(e,t,n){this.props.handleConfirmBooking(e,t,n)}},{key:"render",value:function(){var e=this,t=this.props.selectedMovie,n=this.props.selectedMovieTime,o=this.state.selectedSeat,s=JSON.parse("["+n.hall.layout+"]");return Object(k.jsxs)("div",{children:[Object(k.jsx)(p.a,{id:"row1",type:"checkbox",variant:"primary",children:Object(k.jsx)(S.a,{striped:!0,bordered:!0,hover:!0,variant:"dark",children:Object(k.jsx)("tbody",{children:s.map((function(t,n){return Object(k.jsx)("tr",{children:t.map((function(t,n){return Object(k.jsx)("td",{children:Object(k.jsx)(f.a,{id:"tbg-check-{subItems}",value:t,variant:o.some((function(e){return t===e}))?"warning":"primary",onClick:function(n){return e.handleChange(n,t)},children:t})})}))})}))})})}),Object(k.jsx)("div",{children:Object(k.jsx)(j.a,{onClick:function(){return e.confirmBooking(t,n,o)},children:"Confirm Booking"})})]})}}]),n}(o.Component)),x=n(65),y=n(154),T=n(153),w=function(e){Object(r.a)(n,e);var t=Object(h.a)(n);function n(){var e;return Object(c.a)(this,n),(e=t.call(this)).state={name:"React",showBookingDetail:!1,showHall:!1,selectedMovie:null,selectedMovieTime:null,selectedSeat:null,jwt_token:null,booking:null,soldseat:null},e.bookTicket=e.bookTicket.bind(Object(d.a)(e)),e.cancelTicket=e.cancelTicket.bind(Object(d.a)(e)),e.purchaseTicket=e.purchaseTicket.bind(Object(d.a)(e)),e.selectSeat=e.selectSeat.bind(Object(d.a)(e)),e.confirmBooking=e.confirmBooking.bind(Object(d.a)(e)),e}return Object(l.a)(n,[{key:"bookTicket",value:function(e){var t=this;console.log(e),fetch("http://localhost:8080/movies?mvid="+e).then((function(e){return e.json()})).then((function(e){console.log(e[0]),t.setState({selectedMovie:e[0]},(function(){return t.ReserveSeat()})),console.log("set data done")})).catch(console.log)}},{key:"selectSeat",value:function(e,t){var n=this;this.setState({selectedMovie:e}),this.setState({selectedMovieTime:t});var o=t.id;fetch("http://localhost:8080/api/movie/v1/seatstatus",{method:"POST",body:o}).then((function(e){if(console.log(e),e.ok){var t=e.headers.get("request-hash");return n.setState({jwt_token:t}),console.log(e),e}throw new Error(e.status)})).then((function(e){console.log(e),n.setState({soldseat:e}),n.setState({showHall:!0})})).catch((function(e){console.log("error: "+e),n.setState({showHall:!0}),n.setState({soldseat:null}),n.setState({selectedMovie:null}),n.setState({selectedMovieTime:null}),alert("Error, failed to get data")}))}},{key:"confirmBooking",value:function(e,t,n){var o=this;this.setState({selectedSeat:n}),this.setState({selectedMovie:e}),this.setState({selectedMovieTime:t}),this.setState({showHall:!1}),alert(this.state.jwt_token);var s={id:Object(T.a)(this.state.jwt_token).sub},i={method:"POST",headers:{"Content-Type":"application/json","request-hash":this.state.jwt_token},body:JSON.stringify(s)};fetch("http://localhost:8080/api/booking/v1/reserve",i).then((function(e){if(console.log(e),e.ok)return e;throw new Error(e.status)})).then((function(t){console.log(e),o.setState({showBookingDetail:!0})})).catch((function(e){console.log("error: "+e),o.setState({showBookingDetail:!1}),o.setState({selectedSeat:null}),o.setState({selectedMovie:null}),o.setState({selectedMovieTime:null}),alert("Sorry, The seat(s) that you selected is taken by others")}))}},{key:"cancelTicket",value:function(e){this.setState({selectedMovie:null}),this.setState({showBookingDetail:!1})}},{key:"purchaseTicket",value:function(e){this.setState({selectedMovie:null}),this.setState({showBookingDetail:!1})}},{key:"render",value:function(){var e=this,t=this.state.showBookingDetail,n=this.state.showHall,o=this.props.movies,s=this.state.selectedMovie,i=this.state.selectedMovieTime;return Object(k.jsxs)("div",{children:[t&&Object(k.jsx)(O,{movie:this.state.selectedMovie,movieTime:this.state.selectedMovieTime,selectedSeat:this.state.selectedSeat,handleClose:this.cancelTicket,handlePurchase:this.purchaseTicket}),Object(k.jsx)("center",{children:Object(k.jsx)("h1",{children:"Movie List"})}),Object(k.jsx)(y.a,{children:o.map((function(t){return Object(k.jsx)(x.a,{style:{width:"18rem"},children:Object(k.jsxs)(x.a.Body,{children:[Object(k.jsx)(x.a.Title,{children:t.name}),Object(k.jsx)(x.a.Text,{children:"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sodales volutpat sodales."}),null!=t.movieTime&&t.movieTime.map((function(n){return Object(k.jsx)(j.a,{variant:"link",onClick:function(){return e.selectSeat(t,n)},movie:t,movieTime:n,children:n.time},n.id)}))]})},t.id)}))}),n&&Object(k.jsx)(g,{handleConfirmBooking:this.confirmBooking,selectedMovie:s,selectedMovieTime:i})]})}}]),n}(o.Component),C=function(e){Object(r.a)(n,e);var t=Object(h.a)(n);function n(){var e;Object(c.a)(this,n);for(var o=arguments.length,s=new Array(o),i=0;i<o;i++)s[i]=arguments[i];return(e=t.call.apply(t,[this].concat(s))).state={movies:[],showModal:!1},e}return Object(l.a)(n,[{key:"componentDidMount",value:function(){var e=this;fetch("http://localhost:8080/api/movie/v1/list").then((function(e){return e.json()})).then((function(t){e.setState({movies:t})})).catch(console.log)}},{key:"render",value:function(){return Object(k.jsx)("div",{className:"card",children:Object(k.jsx)("div",{className:"card-body",children:Object(k.jsx)(w,{movies:this.state.movies})})})}}]),n}(o.Component),M=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,469)).then((function(t){var n=t.getCLS,o=t.getFID,s=t.getFCP,i=t.getLCP,a=t.getTTFB;n(e),o(e),s(e),i(e),a(e)}))};a.a.render(Object(k.jsx)(s.a.StrictMode,{children:Object(k.jsx)(C,{})}),document.getElementById("root")),M()}},[[466,1,2]]]);
//# sourceMappingURL=main.2760d3ff.chunk.js.map