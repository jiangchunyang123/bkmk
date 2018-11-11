package com.eve.bookmarks.entitys.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookMarkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public BookMarkExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMysqlIdIsNull() {
            addCriterion("mysql_id is null");
            return (Criteria) this;
        }

        public Criteria andMysqlIdIsNotNull() {
            addCriterion("mysql_id is not null");
            return (Criteria) this;
        }

        public Criteria andMysqlIdEqualTo(Long value) {
            addCriterion("mysql_id =", value, "mysqlId");
            return (Criteria) this;
        }

        public Criteria andMysqlIdNotEqualTo(Long value) {
            addCriterion("mysql_id <>", value, "mysqlId");
            return (Criteria) this;
        }

        public Criteria andMysqlIdGreaterThan(Long value) {
            addCriterion("mysql_id >", value, "mysqlId");
            return (Criteria) this;
        }

        public Criteria andMysqlIdGreaterThanOrEqualTo(Long value) {
            addCriterion("mysql_id >=", value, "mysqlId");
            return (Criteria) this;
        }

        public Criteria andMysqlIdLessThan(Long value) {
            addCriterion("mysql_id <", value, "mysqlId");
            return (Criteria) this;
        }

        public Criteria andMysqlIdLessThanOrEqualTo(Long value) {
            addCriterion("mysql_id <=", value, "mysqlId");
            return (Criteria) this;
        }

        public Criteria andMysqlIdIn(List<Long> values) {
            addCriterion("mysql_id in", values, "mysqlId");
            return (Criteria) this;
        }

        public Criteria andMysqlIdNotIn(List<Long> values) {
            addCriterion("mysql_id not in", values, "mysqlId");
            return (Criteria) this;
        }

        public Criteria andMysqlIdBetween(Long value1, Long value2) {
            addCriterion("mysql_id between", value1, value2, "mysqlId");
            return (Criteria) this;
        }

        public Criteria andMysqlIdNotBetween(Long value1, Long value2) {
            addCriterion("mysql_id not between", value1, value2, "mysqlId");
            return (Criteria) this;
        }

        public Criteria andCrateTimeIsNull() {
            addCriterion("crate_time is null");
            return (Criteria) this;
        }

        public Criteria andCrateTimeIsNotNull() {
            addCriterion("crate_time is not null");
            return (Criteria) this;
        }

        public Criteria andCrateTimeEqualTo(Date value) {
            addCriterion("crate_time =", value, "crateTime");
            return (Criteria) this;
        }

        public Criteria andCrateTimeNotEqualTo(Date value) {
            addCriterion("crate_time <>", value, "crateTime");
            return (Criteria) this;
        }

        public Criteria andCrateTimeGreaterThan(Date value) {
            addCriterion("crate_time >", value, "crateTime");
            return (Criteria) this;
        }

        public Criteria andCrateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("crate_time >=", value, "crateTime");
            return (Criteria) this;
        }

        public Criteria andCrateTimeLessThan(Date value) {
            addCriterion("crate_time <", value, "crateTime");
            return (Criteria) this;
        }

        public Criteria andCrateTimeLessThanOrEqualTo(Date value) {
            addCriterion("crate_time <=", value, "crateTime");
            return (Criteria) this;
        }

        public Criteria andCrateTimeIn(List<Date> values) {
            addCriterion("crate_time in", values, "crateTime");
            return (Criteria) this;
        }

        public Criteria andCrateTimeNotIn(List<Date> values) {
            addCriterion("crate_time not in", values, "crateTime");
            return (Criteria) this;
        }

        public Criteria andCrateTimeBetween(Date value1, Date value2) {
            addCriterion("crate_time between", value1, value2, "crateTime");
            return (Criteria) this;
        }

        public Criteria andCrateTimeNotBetween(Date value1, Date value2) {
            addCriterion("crate_time not between", value1, value2, "crateTime");
            return (Criteria) this;
        }

        public Criteria andDateAddedIsNull() {
            addCriterion("date_added is null");
            return (Criteria) this;
        }

        public Criteria andDateAddedIsNotNull() {
            addCriterion("date_added is not null");
            return (Criteria) this;
        }

        public Criteria andDateAddedEqualTo(String value) {
            addCriterion("date_added =", value, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateAddedNotEqualTo(String value) {
            addCriterion("date_added <>", value, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateAddedGreaterThan(String value) {
            addCriterion("date_added >", value, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateAddedGreaterThanOrEqualTo(String value) {
            addCriterion("date_added >=", value, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateAddedLessThan(String value) {
            addCriterion("date_added <", value, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateAddedLessThanOrEqualTo(String value) {
            addCriterion("date_added <=", value, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateAddedLike(String value) {
            addCriterion("date_added like", value, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateAddedNotLike(String value) {
            addCriterion("date_added not like", value, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateAddedIn(List<String> values) {
            addCriterion("date_added in", values, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateAddedNotIn(List<String> values) {
            addCriterion("date_added not in", values, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateAddedBetween(String value1, String value2) {
            addCriterion("date_added between", value1, value2, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateAddedNotBetween(String value1, String value2) {
            addCriterion("date_added not between", value1, value2, "dateAdded");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedIsNull() {
            addCriterion("date_group_modified is null");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedIsNotNull() {
            addCriterion("date_group_modified is not null");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedEqualTo(String value) {
            addCriterion("date_group_modified =", value, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedNotEqualTo(String value) {
            addCriterion("date_group_modified <>", value, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedGreaterThan(String value) {
            addCriterion("date_group_modified >", value, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedGreaterThanOrEqualTo(String value) {
            addCriterion("date_group_modified >=", value, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedLessThan(String value) {
            addCriterion("date_group_modified <", value, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedLessThanOrEqualTo(String value) {
            addCriterion("date_group_modified <=", value, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedLike(String value) {
            addCriterion("date_group_modified like", value, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedNotLike(String value) {
            addCriterion("date_group_modified not like", value, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedIn(List<String> values) {
            addCriterion("date_group_modified in", values, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedNotIn(List<String> values) {
            addCriterion("date_group_modified not in", values, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedBetween(String value1, String value2) {
            addCriterion("date_group_modified between", value1, value2, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andDateGroupModifiedNotBetween(String value1, String value2) {
            addCriterion("date_group_modified not between", value1, value2, "dateGroupModified");
            return (Criteria) this;
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdxIsNull() {
            addCriterion("idx is null");
            return (Criteria) this;
        }

        public Criteria andIdxIsNotNull() {
            addCriterion("idx is not null");
            return (Criteria) this;
        }

        public Criteria andIdxEqualTo(String value) {
            addCriterion("idx =", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotEqualTo(String value) {
            addCriterion("idx <>", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxGreaterThan(String value) {
            addCriterion("idx >", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxGreaterThanOrEqualTo(String value) {
            addCriterion("idx >=", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxLessThan(String value) {
            addCriterion("idx <", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxLessThanOrEqualTo(String value) {
            addCriterion("idx <=", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxLike(String value) {
            addCriterion("idx like", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotLike(String value) {
            addCriterion("idx not like", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxIn(List<String> values) {
            addCriterion("idx in", values, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotIn(List<String> values) {
            addCriterion("idx not in", values, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxBetween(String value1, String value2) {
            addCriterion("idx between", value1, value2, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotBetween(String value1, String value2) {
            addCriterion("idx not between", value1, value2, "idx");
            return (Criteria) this;
        }

        public Criteria andMongoIdIsNull() {
            addCriterion("mongo_id is null");
            return (Criteria) this;
        }

        public Criteria andMongoIdIsNotNull() {
            addCriterion("mongo_id is not null");
            return (Criteria) this;
        }

        public Criteria andMongoIdEqualTo(String value) {
            addCriterion("mongo_id =", value, "mongoId");
            return (Criteria) this;
        }

        public Criteria andMongoIdNotEqualTo(String value) {
            addCriterion("mongo_id <>", value, "mongoId");
            return (Criteria) this;
        }

        public Criteria andMongoIdGreaterThan(String value) {
            addCriterion("mongo_id >", value, "mongoId");
            return (Criteria) this;
        }

        public Criteria andMongoIdGreaterThanOrEqualTo(String value) {
            addCriterion("mongo_id >=", value, "mongoId");
            return (Criteria) this;
        }

        public Criteria andMongoIdLessThan(String value) {
            addCriterion("mongo_id <", value, "mongoId");
            return (Criteria) this;
        }

        public Criteria andMongoIdLessThanOrEqualTo(String value) {
            addCriterion("mongo_id <=", value, "mongoId");
            return (Criteria) this;
        }

        public Criteria andMongoIdLike(String value) {
            addCriterion("mongo_id like", value, "mongoId");
            return (Criteria) this;
        }

        public Criteria andMongoIdNotLike(String value) {
            addCriterion("mongo_id not like", value, "mongoId");
            return (Criteria) this;
        }

        public Criteria andMongoIdIn(List<String> values) {
            addCriterion("mongo_id in", values, "mongoId");
            return (Criteria) this;
        }

        public Criteria andMongoIdNotIn(List<String> values) {
            addCriterion("mongo_id not in", values, "mongoId");
            return (Criteria) this;
        }

        public Criteria andMongoIdBetween(String value1, String value2) {
            addCriterion("mongo_id between", value1, value2, "mongoId");
            return (Criteria) this;
        }

        public Criteria andMongoIdNotBetween(String value1, String value2) {
            addCriterion("mongo_id not between", value1, value2, "mongoId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("parent_id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("parent_id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdIsNull() {
            addCriterion("parent_mysql_id is null");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdIsNotNull() {
            addCriterion("parent_mysql_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdEqualTo(Long value) {
            addCriterion("parent_mysql_id =", value, "parentMysqlId");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdNotEqualTo(Long value) {
            addCriterion("parent_mysql_id <>", value, "parentMysqlId");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdGreaterThan(Long value) {
            addCriterion("parent_mysql_id >", value, "parentMysqlId");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_mysql_id >=", value, "parentMysqlId");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdLessThan(Long value) {
            addCriterion("parent_mysql_id <", value, "parentMysqlId");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_mysql_id <=", value, "parentMysqlId");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdIn(List<Long> values) {
            addCriterion("parent_mysql_id in", values, "parentMysqlId");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdNotIn(List<Long> values) {
            addCriterion("parent_mysql_id not in", values, "parentMysqlId");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdBetween(Long value1, Long value2) {
            addCriterion("parent_mysql_id between", value1, value2, "parentMysqlId");
            return (Criteria) this;
        }

        public Criteria andParentMysqlIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_mysql_id not between", value1, value2, "parentMysqlId");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("path is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("path is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("path =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("path <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("path >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("path >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("path <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("path <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("path like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("path not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("path in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("path not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("path between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("path not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(String value) {
            addCriterion("uid like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(String value) {
            addCriterion("uid not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}