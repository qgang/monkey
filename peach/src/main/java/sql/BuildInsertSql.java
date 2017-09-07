package sql;

import Util.FileUtil;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steelqin on 17/1/9.
 */
public class BuildInsertSql {
    public static void main(String[] args) {
        BuildInsertSql sql = new BuildInsertSql();
        sql.start();
    }

    private static final String INPUT_FILE_PATH = "data/cateringCategory.txt";
    private static final String OUTPUT_FILE_PATH = "/Users/steelqin/Documents/sqlOut.sql";

    private void start() {
        List<Entity> entityList = getEntityFromFile(INPUT_FILE_PATH);
        List<String> insertSqlStr = buildInsertSqlStrByEntity(entityList);
        write2File(OUTPUT_FILE_PATH, insertSqlStr);
    }

    private void write2File(String outputFilePath, List<String> insertSqlStr){
        if (CollectionUtils.isEmpty(insertSqlStr)) {
            return;
        }
        BufferedWriter bw = FileUtil.openFileWriter(outputFilePath, false);
        try {
            for (String str : insertSqlStr) {
                bw.write(str + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtil.close(bw);
        }
    }

    private List<String> buildInsertSqlStrByEntity(List<Entity> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return ListUtils.EMPTY_LIST;
        }
        List<String> sqls = new ArrayList<>(111);
        sqls.add("update `SB_Article_Category` set `name` = '美食' where `category_id` = 70 and `parent_id` = 0;\n" +
                "update `SB_Article_Category` set `name` = '蟹宴' where `category_id` = 704 and `parent_id` = 70;\n");

        for (Entity entity : entityList) {
            StringBuilder sb = new StringBuilder();
            sb.append("insert into `SB_Article_Category` (`category_id`,`parent_id`,`name`,`type`,`status`,`description`,`update_by`,`updateTime`,`display`,`display_order`) " +
                    "values (");
            sb.append(entity.categoryId + ",");
            sb.append(entity.parentId + ",");
            sb.append("'" + entity.name + "',");
            sb.append(entity.type + ",");
            sb.append(entity.status + ",");
            sb.append(entity.description + ",");
            sb.append(entity.updateBy + ",");
            sb.append(entity.updateTime + ",");
            sb.append(entity.display + ",");
            sb.append(entity.displayOrder);
            sb.append(");");
            sqls.add(sb.toString());
        }
        return sqls;
    }

    private List<Entity> getEntityFromFile(String inputFilePath) {
        InputStream inputStream = null;
        try {
            Resource resource = new ClassPathResource(inputFilePath);
            if (resource.isReadable()) {
                inputStream = resource.getInputStream();
                List<String> entityList = IOUtils.readLines(inputStream);
                return parserList(entityList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return null;
    }

    private List<Entity> parserList(List<String> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return ListUtils.EMPTY_LIST;
        }
        List<Entity> parserList = new ArrayList<>(111);
        for (String str : entityList) {
            try {
                String[] strs = str.split("\\t");
                Entity entity = new Entity();
                entity.categoryId = strs[0];
                entity.name = strs[2];
                parserList.add(entity);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        return parserList;
    }

    private class Entity{
        private String categoryId;
        private String parentId = "70";
        private String name;
        private String type = "70";
        private String status = "0";
        private String description = null;
        private String updateBy = null;
        private String updateTime = "NOW()";
        private String display = "0";
        private String displayOrder = "0";
    }
}
