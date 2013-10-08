# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create

svn_log_file = File.open('db/data/svn_log.xml')
svn_log_hash = Hash.from_xml(svn_log_file.read)

svn_list_file = File.open('db/data/svn_list.xml')
svn_list_hash = Hash.from_xml(svn_list_file.read)

for entry in svn_list_hash['lists']['list']['entry']
  project_name = entry['name'].split('/').first
  project = Project.find_by_title(project_name)
  if  project.nil?
    project = Project.create(title: project_name)
    #attr_accessible :date, :summary, :title, :version
  end
  if entry['kind'] == 'file'
    path = entry['name']
    name = path.split('/').last
    file_type = name.split('.').last
    record = project.file_records.create(path: entry['name'], size: entry['size'].to_i,name: name, file_type: file_type)
    commit = entry['commit']
    record.file_versions.create(author: commit['author'], date: commit['date'], revision: commit['revision'] )
    #:author, :date, :file_record_id, :message, :revision
  end
end


