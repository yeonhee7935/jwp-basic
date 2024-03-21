<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/template.jsp"%>
   <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-default content-main">
          <form name="question" method="post" action="/user/update">
              <div class="form-group">
                  <label for="userId">사용자 아이디</label>
                  <input class="form-control" id="userId" name="userId"  readonly="readonly" value="${user.userId}">
              </div>
              <div class="form-group">
                  <label for="password">비밀번호</label>
                  <input type="password" class="form-control" id="password" name="password"  value="${user.password}">
              </div>
              <div class="form-group">
                  <label for="name">이름</label>
                  <input class="form-control" id="name" name="name"  value="${user.name}">
              </div>
              <div class="form-group">
                  <label for="email">이메일</label>
                  <input type="email" class="form-control" id="email" name="email"  value="${user.email}">
              </div>
              <button type="submit" class="btn btn-success clearfix pull-right">개인정보수정</button>
              <div class="clearfix" />
          </form>
        </div>
    </div>
</div>

<!-- script references -->
<script src="../js/jquery-2.2.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/scripts.js"></script>
	</body>
</html>