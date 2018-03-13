package com.example.demo.dao.ch.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.ch.BaseDao;
import com.example.demo.dao.ch.IInspectionDao;
import com.example.demo.dao.ch.IMedicalHistoryDao;
import com.example.demo.entity.ch.Inspection;
import com.example.demo.entity.ch.MedicalHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class InspectionDaoImpl extends BaseDao implements IInspectionDao {

    @Override
    protected String generateQuerySql() {
        String sql = "select * from `检查报告`";
        return sql;
    }

    @Override
    protected <T> RowMapper<T> generateRowMapper() {
        if (getRowMapper() == null) {
            setRowMapper(new InspectionRowMapper());
        }
        return getRowMapper();
    }

    @Override
    public List<Inspection> findInspectionRecord(String dataSource, int pageNum, int pageSize) {
        return super.queryForList(getJdbcTemplate(dataSource), pageNum, pageSize);
    }

    @Override
    public List<String> findOrgOdCatByGroupRecordName(String dataSource, String groupRecordName) {
        return super.findOrgOdCatByGroupRecordName(dataSource, groupRecordName);
    }

    @Override
    public void batchInsert2HRS(List<JSONObject> records, String collectionName) {
        synchronized (this) {
            hrsMongoTemplate.insert(records, collectionName);
        }
    }

    class InspectionRowMapper implements RowMapper<Inspection> {

        @Override
        public Inspection mapRow(ResultSet rs, int rowNum) throws SQLException {
            Inspection inspection = new Inspection();
            inspection.setId(rs.getInt("id"));
            inspection.setGroupRecordName(rs.getString(Inspection.ColumnMapping.GROUP_RECORD_NAME.columnName()));
            inspection.setPatientId(rs.getString(Inspection.ColumnMapping.PATIENT_ID.columnName()));
            inspection.setAbnormalFlag(rs.getString(Inspection.ColumnMapping.ABNORMAL_FLAG.columnName()));

            inspection.setResultContent(rs.getString(Inspection.ColumnMapping.RESULT_CONTENT.columnName()));
            inspection.setResultDesc(rs.getString(Inspection.ColumnMapping.RESULT_DESC.columnName()));
            inspection.setInspectionState(rs.getString(Inspection.ColumnMapping.INSPECTION_STATE.columnName()));
            inspection.setReportId(rs.getString(Inspection.ColumnMapping.REPORT_ID.columnName()));
            inspection.setReportFixDate(rs.getString(Inspection.ColumnMapping.REPORT_FIX_DATE.columnName()));
            inspection.setTypeName(rs.getString(Inspection.ColumnMapping.TYPE_NAME.columnName()));
            inspection.setApplyDate(rs.getString(Inspection.ColumnMapping.APPLY_DATE.columnName()));
            inspection.setBirthday(rs.getString(Inspection.ColumnMapping.BIRTHDAY.columnName()));
            inspection.setDoctorName(rs.getString(Inspection.ColumnMapping.DOCTOR_NAME.columnName()));
            inspection.setHospitalFlag(rs.getString(Inspection.ColumnMapping.HOSPITAL_FLAG.columnName()));
            inspection.setAuditor(rs.getString(Inspection.ColumnMapping.AUDITOR.columnName()));
            inspection.setObserveReason(rs.getString(Inspection.ColumnMapping.OBSERVE_REASON.columnName()));
            inspection.setInspectionMethod(rs.getString(Inspection.ColumnMapping.INSPECTION_METHOD.columnName()));
            inspection.setDiagnosis(rs.getString(Inspection.ColumnMapping.DIAGNOSIS.columnName()));
            inspection.setApplyNo(rs.getString(Inspection.ColumnMapping.APPLY_NO.columnName()));

            inspection.setSex(rs.getString(Inspection.ColumnMapping.SEX.columnName()));
            inspection.setReportDate(rs.getString(Inspection.ColumnMapping.REPORT_DATE.columnName()));
            inspection.setAuditDate(rs.getString(Inspection.ColumnMapping.AUDIT_DATE.columnName()));
            inspection.setApplyProjectName(rs.getString(Inspection.ColumnMapping.APPLY_PROJECT_NAME.columnName()));
            inspection.setAdvice(rs.getString(Inspection.ColumnMapping.ADVICE.columnName()));
            inspection.setAge(rs.getString(Inspection.ColumnMapping.AGE.columnName()));

            return inspection;
        }
    }

}
